# My Med App !

### A CPSC 210 Project
### Phase 3: Instructions for Grader

- You can generate the first action related of adding Prescriptions to a Patient Account by inputting the required 
    fields to make a prescription and then clicking the "Add Prescription" button 
  - NOTES: the date field format must follow (YYYY-MM-DD)
- You can generate the second action related to removing Prescriptions from a Patient Account by selecting the
    prescription to be removed and then clicking the "Remove Prescription" button.
- You can reschedule the first dose of the prescription by selecting a prescription in the table and inputting the
desired reschedule time
  - NOTES: the reschedule time input text must be in the format HH:MM ie. (03:00) for the function to work
- You can locate my visual component in the middle of the panel
- You can save the state of my application by clicking the "Save Account" button
- You can reload the state of my application by clicking the "Load Account" button

### Project Overview

<p>The design goal of this project entails implementing a working application that enables users to efficiently track 
personal prescription data over time with the aim of improving medicine adherence. Overall, the 
interface aims to aid a user demographic of patients that 
regularly take medication or utilize routine medical practices (eg. diabetes therapy/injection) with independent
administration of medication and monitoring; data abstraction includes representing general information 
including dosage,precautions and potential side effects of prescriptions, as well as personal tools that the user 
can utilize totrack the quantity of their prescription over time and symptoms that can be recorded for future reference.
Holistically, the program will be able to represent the patient user's personal information 
inclusive of diagnoses, preferredpharmacy/healthcare provider, overall list of prescriptions, 
prescription history and schedule of medications 
to be administered in the next 24-hour period. Within the list of prescriptions, each individual instance of 
medicine/prescription will include attributes such as treatment overview (purpose and usage), quantity of prescription, 
future refill dates, historical refill dates and the ability to customize input of the amount of treatments left. 
Potential methods will allow the patient to indicate whether they missed a dose or administered multiple amounts within
the scheduled timeframe, enabling respective incrementation up or down of medicine quantity. The list of prescriptions
will also include methods allowing a patient to update the list, while the scheduling class will include capacities to
schedule pills according to specified dosage times (eg. every twelve hours, every 24 hours), accept input from the user 
regarding time dosage was taken, inform the patient of the last time a pill was administered and reset a 
prescription schedule depending on circumstances when dosages are not followed. 

### Personal Input 
<p>The idea for this project was originated from my personal difficulties with remembering to take my prescribed
medication on a daily schedule. Often, I struggle with recalling whether I took a pill at a certain time, and 
quantifying the amount of pills I have left to take and when to request my next refill. This application aims to aid 
similar individuals who have trouble with self-monitoring prescription medication through conveniently providing 
information on schedule, inventory and dosage instructions.</p>

## User Stories

###### As a user, I want to be able to:

* Add all of my prescriptions (X) to my patient account(Y):
  * Inclusive of being able to make a new prescription and add it to the list of prescriptions

* Remove a prescription from the list of prescriptions in a patient account 

* View attributes for a patient (Y) inclusive of:
  * list of prescriptions (listOfX)
  * daily dosage schedule (inclusive of every prescription (X) in prescription list)
  * symptoms

* View attributes of each prescription (X) inclusive of: 
  * imprint id
  * number of pills left in the bottle
  * treatment instructions (eg. split pill, ingest orally, before meal, before sleep)
  * schedule to take dosage 
  * buffer time between dosages
  * frequency of dosages per day 
  * last prescription refill date 
  * expected next prescription refill date 
  
* Edit/change the dosage schedule for each individual prescription (X)

* Choose to save the holistic state of my prescription account, inclusive of prescriptions and their individual
  attributes to file

* Access and reload the state of my prescription account, inclusive of their individual attributes from file 
  * (eg. schedule, dosage amount etc.) 

### "Phase 4: Task 2"
Successfully saved file
Saved Janaye Cheong to ./data/patientaccountGUI.json
Closing application... recording events.
Thu Apr 13 04:04:47 PDT 2023
escitalopram was added to the prescription list
Thu Apr 13 04:04:47 PDT 2023
zoloft was added to the prescription list
Thu Apr 13 04:05:11 PDT 2023
wellbutrin was added to the prescription list
Thu Apr 13 04:05:16 PDT 2023
Starting dose corresponding to wellbutrin has been changed to 12:00
Thu Apr 13 04:05:21 PDT 2023
escitalopram was removed from the prescription list

### "Phase 4: Task 3"
This prescription app facilitated data abstraction by allowing discrete classes in the model to represent individual 
Prescription elements related to an overarching Patient Account; JsonReader and JsonWriter classes in persistence
to store objects from the model in files; and UI classes that were able to provide a console functionality to the app
as well as a graphical user interface. In particular, the separation between the two model classes (Patient Account &
Prescription) and the ability of the Prescription to be listed as a Collection within the Patient Account class 
allowed dynamic methods to be implemented in both that were cohesive and unique to the specific class. For example, 
the methods that initiated an initial dose for the prescription as well as rescheduled its first dose were implemented
and called by the specific Prescription; alternatively, the Patient Account class possessed the functionality of adding 
or removing separate prescriptions, and also possessed fields belonging to the unique patient (overall symptoms, 
identification, etc)

In terms of future refactoring, cohesion could be improved according to the single responsibility design principle by 
separating the methods associated with the default and edited scheduling in the Prescription class
(setDoseSchedule() and editSchedule()) to a separate class labelled ScheduleManager. This act of refactoring would
clarify the methods and behaviours involved with the prescription itself in managing doses and dosage information,
while associating temporal data including the prescription schedule in another class that would be referenced in the 
Patient Account. The principle of cohesion could similarly be improved in MyPrescriptionGUI by intiating different
UI classes that encompassed different panels, so that individual management over the presentation and information
in the three button, image and table panels could be managed individually and then initiated by a main method in 
MyPrescriptionGUI. Another salient opportunity to refactor code would be associated with the storage of the data 
objects in the JTable. Rather than updating the JTable Model as well as the PatientAccount when removing or
adding pills, one could store the actual objects within the Table Model and reduce the areas where data is stored. 
Overall, the PrescriptionApp overlay and dynamic functionality could be refactored in future instances to optimize
presentation, reduce possible bugs and improve cohesion. 


//sources cited:
https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html
https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html
https://www.educative.io/answers/how-to-convert-a-double-to-int-in-java
https://dencode.com/en/date/iso8601
http://stleary.github.io/JSON-java/index.html
https://docs.oracle.com/javase/tutorial/uiswing/events/windowlistener.html
https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html#windowevents
https://docs.oracle.com/javase/7/docs/api/java/awt/event/WindowAdapter.html

