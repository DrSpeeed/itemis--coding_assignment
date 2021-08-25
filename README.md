# itemis--coding_assignment

This code addresses problem 2 of the coding assignment, the CONFERENCE TRACK MANAGEMENT.

Java JDK 11.0.10 was used. The file talks.txt contains the talks provided. 
The application should run without any further building adjustments.

## Annotations on my assumptions

- The task only states that there were 'multiple tracks', the actual number of tracks depend on the number of talks and will dynamically extend to additional tracks if needed.
- The task stated that by rule the titles of talks does not include any number; the method on scraping the length of the talks depends on this rule. If it changes the method must be changed too.
- The tracks were splitted into a morning session and an afternoon session containing a list of talks. The sessions were generated separately. This set-up made it easier to apply the given framework (e.g. lunch break at 12pm).
