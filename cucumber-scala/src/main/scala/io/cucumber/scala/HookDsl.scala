package io.cucumber.scala

private[scala] trait HookDsl extends BaseScalaDsl {
  self =>

  /** Defines a before all hook.
    */
  def BeforeAll: StaticHookBody = BeforeAll(DEFAULT_AFTER_ORDER)

  /** Defines a before all hook.
    * @param order
    *   the order in which this hook should run. Higher numbers are run first
    */
  def BeforeAll(order: Int): StaticHookBody = new StaticHookBody(
    StaticHookType.BEFORE_ALL,
    order,
    Utils.frame(self)
  )

  /** Defines an before hook.
    */
  def Before: HookBody = Before(EMPTY_TAG_EXPRESSION, DEFAULT_BEFORE_ORDER)

  /** Defines an before hook.
    *
    * @param tagExpression
    *   a tag expression, if the expression applies to the current scenario this
    *   hook will be executed
    */
  def Before(tagExpression: String): HookBody =
    Before(tagExpression, DEFAULT_BEFORE_ORDER)

  /** Defines an before hook.
    *
    * @param order
    *   the order in which this hook should run. Higher numbers are run first
    */
  def Before(order: Int): HookBody = Before(EMPTY_TAG_EXPRESSION, order)

  /** Defines an before hook.
    *
    * @param tagExpression
    *   a tag expression, if the expression applies to the current scenario this
    *   hook will be executed
    * @param order
    *   the order in which this hook should run. Higher numbers are run first
    */
  def Before(tagExpression: String, order: Int) =
    new HookBody(
      ScopedHookType.BEFORE,
      tagExpression,
      order,
      Utils.frame(self)
    )

  /** Defines an before step hook.
    */
  def BeforeStep: HookBody =
    BeforeStep(EMPTY_TAG_EXPRESSION, DEFAULT_BEFORE_ORDER)

  /** Defines an before step hook.
    *
    * @param tagExpression
    *   a tag expression, if the expression applies to the current scenario this
    *   hook will be executed
    */
  def BeforeStep(tagExpression: String): HookBody =
    BeforeStep(tagExpression, DEFAULT_BEFORE_ORDER)

  /** Defines an before step hook.
    *
    * @param order
    *   the order in which this hook should run. Higher numbers are run first
    */
  def BeforeStep(order: Int): HookBody = BeforeStep(EMPTY_TAG_EXPRESSION, order)

  /** Defines an before step hook.
    *
    * @param tagExpression
    *   a tag expression, if the expression applies to the current scenario this
    *   hook will be executed
    * @param order
    *   the order in which this hook should run. Higher numbers are run first
    */
  def BeforeStep(tagExpression: String, order: Int) =
    new HookBody(
      ScopedHookType.BEFORE_STEP,
      tagExpression,
      order,
      Utils.frame(self)
    )

  /** Defines a after all hook.
    */
  def AfterAll: StaticHookBody = AfterAll(DEFAULT_AFTER_ORDER)

  /** Defines a after all hook.
    * @param order
    *   the order in which this hook should run. Higher numbers are run first
    */
  def AfterAll(order: Int): StaticHookBody =
    new StaticHookBody(StaticHookType.AFTER_ALL, order, Utils.frame(self))

  /** Defines and after hook.
    */
  def After: HookBody = After(EMPTY_TAG_EXPRESSION, DEFAULT_AFTER_ORDER)

  /** Defines and after hook.
    *
    * @param tagExpression
    *   a tag expression, if the expression applies to the current scenario this
    *   hook will be executed
    */
  def After(tagExpression: String): HookBody =
    After(tagExpression, DEFAULT_AFTER_ORDER)

  /** Defines and after hook.
    *
    * @param order
    *   the order in which this hook should run. Higher numbers are run first
    */
  def After(order: Int): HookBody = After(EMPTY_TAG_EXPRESSION, order)

  /** Defines and after hook.
    *
    * @param tagExpression
    *   a tag expression, if the expression applies to the current scenario this
    *   hook will be executed
    * @param order
    *   the order in which this hook should run. Higher numbers are run first
    */
  def After(tagExpression: String, order: Int) =
    new HookBody(ScopedHookType.AFTER, tagExpression, order, Utils.frame(self))

  /** Defines and after step hook.
    */
  def AfterStep: HookBody = AfterStep(EMPTY_TAG_EXPRESSION, DEFAULT_AFTER_ORDER)

  /** Defines and after step hook.
    *
    * @param tagExpression
    *   a tag expression, if the expression applies to the current scenario this
    *   hook will be executed
    */
  def AfterStep(tagExpression: String): HookBody =
    AfterStep(tagExpression, DEFAULT_AFTER_ORDER)

  /** Defines and after step hook.
    *
    * @param order
    *   the order in which this hook should run. Higher numbers are run first
    */
  def AfterStep(order: Int): HookBody = AfterStep(EMPTY_TAG_EXPRESSION, order)

  /** Defines and after step hook.
    *
    * @param tagExpression
    *   a tag expression, if the expression applies to the current scenario this
    *   hook will be executed
    * @param order
    *   the order in which this hook should run. Higher numbers are run first
    */
  def AfterStep(tagExpression: String, order: Int) =
    new HookBody(
      ScopedHookType.AFTER_STEP,
      tagExpression,
      order,
      Utils.frame(self)
    )

  final class HookBody(
      hookType: ScopedHookType,
      tagExpression: String,
      order: Int,
      frame: StackTraceElement
  ) {

    // When a HookBody is created, we want to ensure that the apply method is called
    // To be able to check this, we notice the registry to expect a hook
    registry.expectHook(hookType, frame)

    def apply(body: => Unit): Unit = {
      apply(_ => body)
    }

    def apply(body: Scenario => Unit): Unit = {
      val details = ScalaHookDetails(tagExpression, order, body, frame)
      registry.registerDynamicHook(hookType, details)
    }

  }

  final class StaticHookBody(
      hookType: StaticHookType,
      order: Int,
      frame: StackTraceElement
  ) {

    // When a HookBody is created, we want to ensure that the apply method is called
    // To be able to check this, we notice the registry to expect a hook
    registry.expectHook(hookType, frame)

    def apply(body: => Unit): Unit = {
      val details = ScalaStaticHookDetails(order, () => body, frame)
      registry.registerStaticHook(hookType, details)
    }

  }

}
