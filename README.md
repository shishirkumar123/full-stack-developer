**Story: Convert NSF Fee Calculation trigger from event-driven consumer to JAMS-invoked API**

**Summary**

Currently, NSF fee calculation is triggered immediately when the ACH return event is consumed, with no gating on the account's cycle date. This creates a gap versus legacy behavior, where NSF fees are only assessed on the account's billing cycle date, and can result in an NSF fee being communicated to the customer before the return itself has been communicated (per Arvil Wright's confirmation — this is a customer-experience requirement, not just legacy behavior).

This story converts the NSF fee calculation entry point from an event-driven Kafka consumer to a REST API exposed by Return Payments Processor, invoked by a JAMS job scheduled to run on the account's cycle date. The initial event-driven consumption (matching and persisting the return record to `NSF_FEE_JOURNAL`) is unaffected and remains as-is.

**Acceptance Criteria**

1. Create a new API endpoint on Return Payments Processor that triggers NSF fee eligibility and calculation processing.
2. Reuse the existing Kafka consumer's NSF fee calculation logic (eligibility check, fee amount calculation, first-vs-repeat determination, capping rules) inside the new API — extract/refactor as needed so the same logic is invoked instead of duplicated.
3. Invoke this API using a JAMS job, scheduled to run on the account's cycle date, matching the pattern used by legacy's `B_NSFFEEADJUSTMENT` job.
4. Remove/disable the existing event-driven trigger that currently kicks off NSF fee calculation immediately upon return-record match — the consumer continues to persist the matched record to `NSF_FEE_JOURNAL` as before, but no longer calculates the fee itself.
5. The new API queries `NSF_FEE_JOURNAL` for records eligible for NSF fee assessment, gated on the account's cycle date.
6. End-to-end test confirms: a matched return record is persisted immediately upon consumption, and NSF fee calculation only occurs when the JAMS job runs for that account's cycle date — not before.
7. Reference ticket SSPP-11614 (NSF fee calculation) is linked for calculation-logic context.

**Open Question**

Should the JAMS job's run schedule support multiple cycle codes per run (as legacy's `GetFdrProcessingCycleCodes` does), or should it be scoped per cycle code separately? Worth confirming with the team before implementation begins.
