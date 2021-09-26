package io.cucumber.scala

import io.cucumber.scala.ScopedHookType._
import io.cucumber.scala.StaticHookType.{AFTER_ALL, BEFORE_ALL}

final class ScalaDslRegistry {

  private var _stepDefinitions: Seq[ScalaStepDetails] = Seq()

  private var _beforeAllHooks: Seq[ScalaStaticHookDetails] = Seq()
  private var _afterAllHooks: Seq[ScalaStaticHookDetails] = Seq()

  private var _beforeHooks: Seq[ScalaHookDetails] = Seq()
  private var _beforeStepHooks: Seq[ScalaHookDetails] = Seq()
  private var _afterHooks: Seq[ScalaHookDetails] = Seq()
  private var _afterStepHooks: Seq[ScalaHookDetails] = Seq()

  private var _undefinedBeforeAllHooks: Seq[UndefinedHook] = Seq()
  private var _undefinedAfterAllHooks: Seq[UndefinedHook] = Seq()

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

  def beforeAllHooks: Seq[ScalaStaticHookDetails] = _beforeAllHooks

  def beforeHooks: Seq[ScalaHookDetails] = _beforeHooks

  def beforeStepHooks: Seq[ScalaHookDetails] = _beforeStepHooks

  def afterAllHooks: Seq[ScalaStaticHookDetails] = _afterAllHooks

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
    val undefinedHook = UndefinedHook(hookType, stackTraceElement)
    hookType match {
      case BEFORE_ALL =>
        _undefinedBeforeAllHooks = _undefinedBeforeAllHooks :+ undefinedHook
      case BEFORE =>
        _undefinedBeforeHooks = _undefinedBeforeHooks :+ undefinedHook
      case BEFORE_STEP =>
        _undefinedBeforeStepHooks = _undefinedBeforeStepHooks :+ undefinedHook
      case AFTER_ALL =>
        _undefinedAfterAllHooks = _undefinedAfterAllHooks :+ undefinedHook
      case AFTER =>
        _undefinedAfterHooks = _undefinedAfterHooks :+ undefinedHook
      case AFTER_STEP =>
        _undefinedAfterStepHooks = _undefinedAfterStepHooks :+ undefinedHook
    }
  }

  def registerDynamicHook(
      hookType: ScopedHookType,
      details: ScalaHookDetails
  ): Unit = {
    hookType match {
      case BEFORE =>
        _beforeHooks = _beforeHooks :+ details
        _undefinedBeforeHooks = _undefinedBeforeHooks.filterNot(
          _.stackTraceElement == details.stackTraceElement
        )
      case BEFORE_STEP =>
        _beforeStepHooks = _beforeStepHooks :+ details
        _undefinedBeforeStepHooks = _undefinedBeforeStepHooks.filterNot(
          _.stackTraceElement == details.stackTraceElement
        )
      case AFTER =>
        _afterHooks = _afterHooks :+ details
        _undefinedAfterHooks = _undefinedAfterHooks.filterNot(
          _.stackTraceElement == details.stackTraceElement
        )
      case AFTER_STEP =>
        _afterStepHooks = _afterStepHooks :+ details
        _undefinedAfterStepHooks = _undefinedAfterStepHooks.filterNot(
          _.stackTraceElement == details.stackTraceElement
        )
    }
  }

  def registerStaticHook(
      hookType: StaticHookType,
      details: ScalaStaticHookDetails
  ): Unit = {
    hookType match {
      case BEFORE_ALL =>
        _beforeAllHooks = _beforeAllHooks :+ details
        _undefinedBeforeAllHooks = _undefinedBeforeAllHooks.filterNot(
          _.stackTraceElement == details.stackTraceElement
        )
      case AFTER_ALL =>
        _afterAllHooks = _afterAllHooks :+ details
        _undefinedAfterAllHooks = _undefinedAfterAllHooks.filterNot(
          _.stackTraceElement == details.stackTraceElement
        )
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

  def checkConsistency(
      scenarioScoped: Boolean
  ): Either[IncorrectHookDefinitionException, Unit] = {
    val undefinedHooks =
      _undefinedBeforeAllHooks ++ _undefinedBeforeHooks ++ _undefinedBeforeStepHooks ++ _undefinedAfterAllHooks ++ _undefinedAfterHooks ++ _undefinedAfterStepHooks
    val staticHooks = _beforeAllHooks ++ _afterAllHooks
    if (scenarioScoped && staticHooks.nonEmpty) {
      Left(new ScenarioScopedStaticHookException(staticHooks))
    } else if (undefinedHooks.nonEmpty) {
      Left(new UndefinedHooksException(undefinedHooks))
    } else {
      Right(())
    }
  }

}
