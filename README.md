Create a new API endpoint on Return Payments Processor that triggers NSF fee eligibility and calculation processing.
Reuse the existing Kafka consumer's NSF fee calculation logic (eligibility check, fee amount calculation, first-vs-repeat determination, capping rules) inside the new API — extract/refactor as needed so the same logic is invoked instead of duplicated.
Invoke this API using a JAMS job, scheduled to run on the account's cycle date, matching the pattern used by legacy's B_NSFFEEADJUSTMENT job.
Remove/disable the existing event-driven trigger that currently kicks off NSF fee calculation immediately upon return-record match — the consumer continues to persist the matched record to NSF_FEE_JOURNAL as before, but no longer calculates the fee itself.
The new API queries NSF_FEE_JOURNAL for records eligible for NSF fee assessment, gated on the account's cycle date.
End-to-end test confirms: a matched return record is persisted immediately upon consumption, and NSF fee calculation only occurs when the JAMS job runs for that account's cycle date — not before.
Reference ticket SSPP-11614 (NSF fee calculation) is linked for calculation-logic context.
