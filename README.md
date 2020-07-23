# Shift Scheduler

Purpose:

The Shift Scheduler solves a real-life scheduling problem. It produces a monthly calendar that contains the names of the employees who are best suited to work on each day in the month, based on the employees' requests.

Function description:

The app takes the following variables into consideration: the dates as requested by the employees, the maximum amount of shifts and the maximum amount of weekends they'd like to work, their preferences as to which colleagues they'd like to work with and which colleagues they'd like to avoid, and the priority of their request. In addition, there must be at least 5 days between two shifts of an employee (as per the customer's specification), and the last five days of the previous month must also be considered. The amount of days between two shifts is currently hard-coded, and it cannot be altered by the user. Another limitation is that the algorithm distributes the names of the employees through up to 4 shift types. This was also specified by the customer. I might make these two parameters into adjustable variables later on in order to make the Shift Scheduler more versatile. In order to give the user more control over the importance of each request, I've added a priority option, so that the user can prioritize requests on a scale from 1 to 5. A priority 1 request gets processed before a priority 2 request, and so on. The result of the calculation is a sheet with 5 columns: the dates in a month, and the shift types 1-4 with the employees' names filled out. 

How it works:

At first the user has to add employees under Employee maintenance. The fields Last name, First name, and Shift type are mandatory. Multiple shift types can be selected. The user can also change an employee's details later on, delete an employee (or delete all employees at once), and view all employees with their phone numbers and shift types. The employee pool is serialized, and it is stored on the user's hard drive.

By clicking on Create shift pool, the user creates a shift pool that is subsequently used to distribute the employees among Shift types 1-4 for the user's convenience, meaning that the user only sees employees of a particular shift when he or she is about to create a request for the employee. In order to create a request, click on Manage shift schedule, and then on Shift type 1-4. When a new employee is added after a shift pool has been already created, the user should click on Reset shift pool, and then Create shift pool in order to see the new employee in the Shift type 1-4 windows. These actions do not affect the requests that have already been entered, since the request pool is stored separately. It is possible to add or alter an employee without having to reenter other employees' requests. 

Once a request for an employee has been entered and stored, the button with the employee's name on it turns green to indicate that a request has been already created for the employee. If the same button is clicked on again, the calendar will display the dates in the employees current request. If the user would like to alter the dates, he has to double click on each date individually in the calendar—the dates from the previous request won't be stored unless the user reselects them. If the user would only like to view the dates, but not to alter them, then he should click on Cancel to exit the Create shift request for [employee] window. If the user clicks on OK, the previous request will be overwritten. If the user clicks on Delete (this button only appears in case a request has been already created for the employee), the request will be deleted without a new request being created.

Be careful—the dates in a request get only overwritten if the user deselects them first, and then selects them (to make the calendar's date fields green) again! If the user doesn't reselect the dates, then the original dates won't get stored in the altered request.

Finally, the user clicks on Calculate shifts to generate a calendar. If the checkbox archive next is selected, then the last 5 days of the newly generated calendar get stored in the archive, and they can be used during next month's calculation. If ignore previous month is selected, then the archived last 5 days will be ignored during the next calculation. 

The user can also decide whether he'd like the algorithm to consider the employees' preferences as to whom they'd like to work with, or whom they'd like to avoid. For optimal results, the user can adjust the priority of each request by reopening any of the shift types in the Manage shift schedule tab, and by altering the employee's request. The user can then change the parameters in the request, save them, and perform a new calculation until the result is a fully filled-out calendar, or until the most possible amount of lines are filled with names. Please note that it is not always possible to fully fill out a calendar (for instance, if no employee has requested to work on a particular day, then this day will remain empty in the calendar).

Short manual:

1.) click on Employee maintenance – Add employee – Save

2.) click on Create shift pool

3.) click on Manage shift schedule – Shift type 1 to Shift type 4

4.) click on the employee's button

5.) select the requested dates, change the shift count, weekend count and priority if needed, combine with other employees if needed (this can be also set up in the Add employee window), then click on OK

6.) select or deselect the checkboxes in the bottom part of the window as needed

7.) click on Calculate shifts


GUI Toolbar description:

View shift pool – displays the current shift pool

Reset shift pool – erases the current shift pool

Create shift pool – generates a new shift pool that is then used to create the buttons with the employees' names on them in the Manage shift schedule tab when the user clicks on Shift type 1-4

Show request pool – displays a table with the requests that have been already entered

Reset request pool – erases all requests. This doesn't affect the employee pool, or the shift pool.

Show archived days – displays the last 5 days of the (previous) month that have been archived in order to use for next month's calculation

Reset archive – erases the data from the archive. Once clicked, the data stored in the archive cannot be used for next month's calculation any longer.

Notes:

The Shift Scheduler app is currently used in a hospital in the Czech Republic, therefore the prefix MUDr. (medical doctor) is added in front of each name in the output calendar.

There are two parts in the app that I didn't create myself. The first part is the GUIcalendarPanel class, the source code for which I'd downloaded from https://blog.eduonix.com/java-programming-2/how-to-use-date-picker-component-in-java/.  I did alter this class heavily, though, by adding the functionality of storing the selected dates (or removing them when deselected) instead of just displaying the dates to the user, and I also added the functionality of displaying the previously entered dates in the calendar whenever a request is reopened. 

The second part I didn't create myself is the IntStream.rangeClosed stream in the weekendFinder() method in the Supercalendar class, but I also altered this one so that the method returns a List<Integer> instead of just printing out the dates. The credit for this block goes to Ortomala Lokni who has posted it here: https://stackoverflow.com/questions/3272454/in-java-get-all-weekend-dates-in-a-given-month


It took me about 6 weeks to design, write, debug and test the Shift Scheduler app.

The runnable file ShiftScheduler.jar that I've uploaded works with JRE 11.0.2 available here: https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html
