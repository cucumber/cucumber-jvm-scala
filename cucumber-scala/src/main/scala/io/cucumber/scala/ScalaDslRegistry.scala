package io.cucumber.scala

import io.cucumber.scala.HookType.{AFTER, AFTER_STEP, BEFORE, BEFORE_STEP}

final class ScalaDslRegistry {

  private var _stepDefinitions: Seq[ScalaStepDetails] = Seq()

  private var _beforeHooks: Seq[ScalaHookDetails] = Seq()
  private var _beforeStepHooks: Seq[ScalaHookDetails] = Seq()
  private var _afterHooks: Seq[ScalaHookDetails] = Seq()
  private var _afterStepHooks: Seq[ScalaHookDetails] = Seq()

  private var _undefinedBeforeHooks: Seq[UndefinedHook] = Seq()
  private var _undefinedBeforeStepHooks: Seq[UndefinedHook] = Seq()
  private var _undefinedAfterHooks: Seq[UndefinedHook] = Seq()
  private var _undefinedAfterStepHooks: Seq[UndefinedHook] = Seq()

  private var _docStringTypes: Seq[ScalaDocStringTypeDetails[_]] = Seq()

  private var _dataTableTypes: Seq[ScalaDataTableTypeDetails[_]] = Seq()

  private var _parameterTypes: Seq[ScalaParameterTypeDetails[_]] = Seq()

  private var _defaultParameterTransformers
      : Seq[ScalaDefaultParameterTransformerDetails] = Seq()

  private var _defaultDataTableCellTransformers
      : Seq[ScalaDefaultDataTableCellTransformerDetails] = Seq()

  private var _defaultDataTableEntryTransformers
      : Seq[ScalaDefaultDataTableEntryTransformerDetails] = Seq()

  def stepDefinitions: Seq[ScalaStepDetails] = _stepDefinitions

  def beforeHooks: Seq[ScalaHookDetails] = _beforeHooks

  def beforeStepHooks: Seq[ScalaHookDetails] = _beforeStepHooks

  def afterHooks: Seq[ScalaHookDetails] = _afterHooks

  def afterStepHooks: Seq[ScalaHookDetails] = _afterStepHooks

  def docStringTypes: Seq[ScalaDocStringTypeDetails[_]] = _docStringTypes

  def dataTableTypes: Seq[ScalaDataTableTypeDetails[_]] = _dataTableTypes

  def parameterTypes: Seq[ScalaParameterTypeDetails[_]] = _parameterTypes

  def defaultParameterTransformers
      : Seq[ScalaDefaultParameterTransformerDetails] =
    _defaultParameterTransformers

  def defaultDataTableCellTransformers
      : Seq[ScalaDefaultDataTableCellTransformerDetails] =
    _defaultDataTableCellTransformers

  def defaultDataTableEntryTransformers
      : Seq[ScalaDefaultDataTableEntryTransformerDetails] =
    _defaultDataTableEntryTransformers

  def expectHook(
      hookType: HookType,
      stackTraceElement: StackTraceElement
  ): Unit = {
    hookType match {
      case BEFORE =>
        _undefinedBeforeHooks =
          _undefinedBeforeHooks :+ UndefinedHook(hookType, stackTraceElement)
      case BEFORE_STEP =>
        _undefinedBeforeStepHooks = _undefinedBeforeStepHooks :+ UndefinedHook(
          hookType,
          stackTraceElement
        )
      case AFTER =>
        _undefinedAfterHooks =
          _undefinedAfterHooks :+ UndefinedHook(hookType, stackTraceElement)
      case AFTER_STEP =>
        _undefinedAfterStepHooks =
          _undefinedAfterStepHooks :+ UndefinedHook(hookType, stackTraceElement)
    }
  }

  def registerHook(
      hookType: HookType,
      details: ScalaHookDetails,
      frame: StackTraceElement
  ): Unit = {
    hookType match {
      case HookType.BEFORE =>
        _beforeHooks = _beforeHooks :+ details
        _undefinedBeforeHooks =
          _undefinedBeforeHooks.filterNot(_.stackTraceElement == frame)
      case HookType.BEFORE_STEP =>
        _beforeStepHooks = _beforeStepHooks :+ details
        _undefinedBeforeStepHooks =
          _undefinedBeforeStepHooks.filterNot(_.stackTraceElement == frame)
      case HookType.AFTER =>
        _afterHooks = _afterHooks :+ details
        _undefinedAfterHooks =
          _undefinedAfterHooks.filterNot(_.stackTraceElement == frame)
      case HookType.AFTER_STEP =>
        _afterStepHooks = _afterStepHooks :+ details
        _undefinedAfterStepHooks =
          _undefinedAfterStepHooks.filterNot(_.stackTraceElement == frame)
    }
  }

  def registerStep(details: ScalaStepDetails): Unit = {
    _stepDefinitions = _stepDefinitions :+ details
  }

  def registerDocStringType[T](details: ScalaDocStringTypeDetails[T]): Unit = {
    _docStringTypes = _docStringTypes :+ details
  }

  def registerDataTableType[T](details: ScalaDataTableTypeDetails[T]): Unit = {
    _dataTableTypes = _dataTableTypes :+ details
  }

  def registerParameterType[R](details: ScalaParameterTypeDetails[R]): Unit = {
    _parameterTypes = _parameterTypes :+ details
  }

  def registerDefaultDataTableCellTransformer(
      details: ScalaDefaultDataTableCellTransformerDetails
  ): Unit = {
    _defaultDataTableCellTransformers =
      _defaultDataTableCellTransformers :+ details
  }

  def registerDefaultDataTableEntryTransformer(
      details: ScalaDefaultDataTableEntryTransformerDetails
  ): Unit = {
    _defaultDataTableEntryTransformers =
      _defaultDataTableEntryTransformers :+ details
  }

  def registerDefaultParameterTransformer(
      details: ScalaDefaultParameterTransformerDetails
  ): Unit = {
    _defaultParameterTransformers = _defaultParameterTransformers :+ details
  }

  def checkConsistency(): Either[IncorrectHookDefinitionException, Unit] = {
    val undefinedHooks =
      _undefinedBeforeHooks ++ _undefinedBeforeStepHooks ++ _undefinedAfterHooks ++ _undefinedAfterStepHooks
    if (undefinedHooks.nonEmpty) {
      Left(new IncorrectHookDefinitionException(undefinedHooks))
    } else {
      Right(())
    }
  }

}
