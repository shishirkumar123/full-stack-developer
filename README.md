Story: Convert NSF Fee Calculation trigger from event-driven consumer to JAMS-invoked API

Summary

Currently, NSF fee calculation is triggered immediately when the ACH return event is consumed, with no gating on the account's cycle date. This creates a gap versus legacy behavior, where NSF fees are only assessed on the account's billing cycle date, and can result in an NSF fee being communicated to the customer before the return itself has been communicated (per Arvil Wright's confirmation — this is a customer-experience requirement, not just legacy behavior).

This story converts the NSF fee calculation entry point from an event-driven Kafka consumer to a REST API exposed by Return Payments Processor, invoked by a JAMS job scheduled to run on the account's cycle date. The initial event-driven consumption (matching and persisting the return record to NSF_FEE_JOURNAL) is unaffected and remains as-is.

Acceptance Criteria

 Return Payments Processor exposes a new API endpoint that triggers NSF fee eligibility and calculation processing.
 The existing event-driven consumer logic that currently triggers NSF fee calculation immediately upon return-record match is removed/disabled; the consumer continues to persist the matched record to NSF_FEE_JOURNAL as before.
 The new API queries NSF_FEE_JOURNAL for records eligible for NSF fee assessment, gated on the account's cycle date (matching legacy's cycle-date rule).
 A JAMS job is configured to invoke the new API on a schedule matching the account cycle-date processing pattern used by the legacy B_NSFFEEADJUSTMENT job.
 NSF fee calculation logic itself (fee amount, first-vs-repeat determination, capping rules) is unchanged from current behavior — only the trigger mechanism changes.
 End-to-end test confirms: a matched return record is persisted immediately upon consumption, and NSF fee calculation only occurs when the JAMS job runs for that account's cycle date — not before.
 Reference ticket SSPP-11614 (NSF fee calculation) is linked for calculation-logic context.
