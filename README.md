Hi team — as part of the NSF fee redesign on our side (moving fee calculation from event-driven to a daily cycle-date-based batch job), we have a question about billing cycle data.

Do you already have an API that returns the list of accounts whose billing cycle date is today?

Why we need this: Our new daily job will calculate NSF fees only for accounts whose billing cycle falls on that day. Rather than pulling cycle codes account-by-account and filtering on our end, it'd be much cleaner if we could ask Accounts directly for "today's eligible accounts" — since cycle date is really account reference data that you own.

If this doesn't exist today, even a simple filter on an existing account-search/list API (e.g., by billing_day_of_month) would work just as well — happy to discuss what's easiest on your end.
