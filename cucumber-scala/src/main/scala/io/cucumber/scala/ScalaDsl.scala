package io.cucumber.scala

/** Base trait for a scala step definition implementation.
  */
trait ScalaDsl
    extends BaseScalaDsl
    with StepDsl
    with HookDsl
    with DataTableTypeDsl
    with DocStringTypeDsl
    with ParameterTypeDsl
    with DefaultTransformerDsl {}
