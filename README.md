This is regarding return payment reprocessing design created by Bhanu.

Legacy analysis ticket - SSPP-11379.
Modern ticket - SSPP-11614.

There is a gap between how legacy behaves and how the modern flow is designed.

Legacy will not assess an NSF fee until the return has actually posted (the day-after rule). In the modern flow, Return Payments Processor fires as soon as the ACH return event arrives — this difference could result in an NSF fee being charged to the customer prematurely.

In order to align with the legacy behavior, I recommend the following change: the initial event-driven consumption from Kafka (matching and persisting the return record) should remain unchanged. Only the NSF fee calculation and assessment step should move to a separate, non-event-driven process. Return Processor should expose an API that identifies eligible return records (based on the day-after posting rule) and triggers NSF fee calculation for them. This API should be invoked by a JAMS job scheduled once a day, around 4 a.m., matching the legacy schedule.

Please let me know your thoughts.
