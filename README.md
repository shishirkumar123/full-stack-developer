Fix: Convert customer identifier format for unmatched return payments (partner service ↔ return service)

Background

SSPP-14443 identified that INDIVIDUAL_IDENTIFIER gets corrupted for unmatched return payments — the return file from Fiserv carries the customer ID in Fiserv's alphanumeric format, but no conversion to the modern UUID format happens before the value is published/persisted, resulting in an incorrect value in both the Unmatched Kafka topic and legacy tables.

Root Cause

No identifier-format conversion currently exists in the unmatched-payment path. The alphanumeric (legacy/Fiserv) customer ID is passed through unchanged, when a UUID (modern) format is expected downstream.

Proposed Fix

Two separate conversions, in two separate services:

ach-payments-service-partner (on unmatched return files only):
Look up the alphanumeric customer ID in system_linkage table
If found: convert to the modern UUID and set it in the existing Kafka payload field before publishing
If not found: send the alphanumeric value as-is, unconverted
return-payment-processor-service (on consuming unmatched events):
If the received value is alphanumeric (conversion didn't happen upstream): ignore it
If the received value is a UUID: convert modern → legacy format using the Hazelcast map (CIAM), which holds the modern-to-legacy customer ID mapping, before sending downstream

Scope

Unmatched case only. Matched case is confirmed working correctly today — no change needed there.
