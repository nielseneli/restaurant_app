# Lab 1 Feedback

Hey Margo + Louise: Here's my feedback for Lab1.

## Specific Feedback

### SQL Issues

When navigating to the Cook's Fragment from the dropdown menu, I got this error:

```
E/AndroidRuntime: FATAL EXCEPTION: main
                  android.database.sqlite.SQLiteException: no such table: entry (code 1): , while compiling: SELECT * FROM entry
                      at android.database.sqlite.SQLiteConnection.nativePrepareStatement(Native Method)
                      at android.database.sqlite.SQLiteConnection.acquirePreparedStatement(SQLiteConnection.java:889)
                      at android.database.sqlite.SQLiteConnection.prepare(SQLiteConnection.java:500)
                      at android.database.sqlite.SQLiteSession.prepare(SQLiteSession.java:588)
                      at android.database.sqlite.SQLiteProgram.<init>(SQLiteProgram.java:58)
                      at android.database.sqlite.SQLiteQuery.<init>(SQLiteQuery.java:37)
                      at android.database.sqlite.SQLiteDirectCursorDriver.query(SQLiteDirectCursorDriver.java:44)
                      at android.database.sqlite.SQLiteDatabase.rawQueryWithFactory(SQLiteDatabase.java:1314)
                      at android.database.sqlite.SQLiteDatabase.rawQuery(SQLiteDatabase.java:1253)
                      at com.margaret.gudfud.ItemsDbHelper.getAllItems(ItemsDbHelper.java:58)
                      at com.margaret.gudfud.CookMenuFragment.onCreateView(CookMenuFragment.java:40)
                      at android.support.v4.app.Fragment.performCreateView(Fragment.java:2080)
                      at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:1108)
                      at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:1290)
                      at android.support.v4.app.BackStackRecord.run(BackStackRecord.java:801)
                      at android.support.v4.app.FragmentManagerImpl.execPendingActions(FragmentManager.java:1677)
                      at android.support.v4.app.FragmentManagerImpl$1.run(FragmentManager.java:536)
                      at android.os.Handler.handleCallback(Handler.java:730)
                      at android.os.Handler.dispatchMessage(Handler.java:92)
                      at android.os.Looper.loop(Looper.java:137)
                      at android.app.ActivityThread.main(ActivityThread.java:5103)
                      at java.lang.reflect.Method.invokeNative(Native Method)
                      at java.lang.reflect.Method.invoke(Method.java:525)
                      at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:737)
                      at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:553)
                      at dalvik.system.NativeStart.main(Native Method)
```

my guess is it's because you're not properly creating the table. This is 
probably why your app worked on Margo's phone but not Louise's. Margo's phone 
already had the table.

I also get the same error when navigating to the Customers Fragment.

I was able to resolve this issue by hardcoding in `onCreate(db)` and forcing the 
SQL table to be created, running it once, then removing this line. This is 
probably why Louise had issues. Your app should have probably ensured that the 
table existed and handled this error.

### Random Bugs

- When you rotate the phone back and forth a bunch it looks like a bunch of 
  fragments are loaded on the screen at the same time. This might be because you 
are hardcoding a fragment into XML, and then also dynamically setting the 
fragment in Java code.
- You get database leak warnings in your code. You're probably not closing your 
  db connections when they're no longer needed.
- Ingredients to Items are not saved and are erased when you leave the screen 
  and then go back to it.

### Usability

Your app was a bit unintuitive to use, it took me a while to figure out that I 
had to type in the name I had placed an order with in the Orders page to see the 
orders for that user. I thought the UIs you made in your design writeup looked 
good, my guess is that you didn't have time to actually implement those UIs.

### Workflow

So, you guys started later than you should have. We've discussed this ad 
nauseum, so I won't drone on about that here. I will say that I'm quite 
impressed with the amount of work you were able to do in 3 days. You got almost 
all functionality working, and wrote a solid amount of good code, so kudos on 
that.

I also feel like the workload was pretty unevenly distributed between the two of 
you, mainly because Louise did not start working on the app until the day 
before. It was pretty unwise to expect that you would be able to contribute a 
fair portion toward an 11 day lab in 1 day. Since this was a group assignment, 
you are both accountable not only for the work that you did but for the work 
your teammates do. When you put off doing your work in this setting, you hurt 
your teammates as much as yourself. You guys also need to make sure your 
teammates are keeping up to make sure the team as a whole is making consistent 
progress.

As far as git practices, I generally saw good commit messages and practices. 
Margo merged some of her own commits which is generally not a *great* idea. 
There were also a few commits straight to master, which I would refrain from 
doing. Even if it's a one line hot-fix, you should open a branch, make the 
change, and PR it in.

## Code Style

Most of your classes had descriptive header comments, which I really liked. I 
also liked the links to stackoverflow, good work on that. You forgot to make a 
few instance variables private.

## Completion

Here are the MVP requirements listed:

### Cooks

[x] There should be a “Menu” with food items such as Hamburgers, Pizza, etc.

[x] Each food item should have an associated ingredients list. (ie, Pizza -> dough, sauce, cheese)

- Sorta, As I noted before, they're not persistent.

[x] Cooks can add/edit/delete food items from the Menu.

[x] Cooks can add/edit/delete ingredients from each menu item’s ingredients list.

[x] Cooks can view “orders”, which each have an associated customer and food item(s).

[ ] Cooks can mark orders as completed.

### Customers

[ ] Customers can view the menu and make orders by selecting one or more menu items, and the quantities of each item (i.e., 2 burgers, 4 pizzas, etc.).

[x] Customers should be able to specify their name when placing an order.

## Your grade

## Lab Rubric(155/200 points total):
#### Proposal (10/10 points)
* 10 - You did the proposal and a teaching team member checked it off

#### Wireframes (30/30 points)
* 30 - You created wireframes for all fragments/screens of your app. You created a rough design of what your app will look like. You clearly outlined the workflow of your app (fragment transitions, how users navigate the app, where the user enters/receives information)

### Final Deliverable (115/160 points)
#### Functionality: 45/70 points
##### Completion: 45/60 points
* 45 - All major features were implemented. You didn't get to one or two small features.

##### Bug free: 5/10 points
* 5 - Your app has one or two bugs, occasionally causing unexpected behavior

#### Design/Usability: 10/15 points
* 10 - A small part of the app was unintuitive to interact with or it was a bit ugly.

#### Quality: 55/75 points

##### Git practices: 10/20 pts

* 10 - Not all code was reviewed before being merged. Each branch did not have a distinct purpose/feature.

##### Good coding practices: 15/20 pts

* 20 - Objects are intelligently designed and used where appropriate, inheritance used when appropriate, code is concise, naming conventions followed, proper access modifiers are used, public getters/setters are used instead of public variables, etc.
* 10 - Some of the above practices were broken, but you mostly followed good practices.

##### Readability: 20/20 pts
* 20 - Functions and variables are named well. Code is well commented where appropriate. Confusing lines are commented. Lines are not too long.

##### Even work distribution: 10/15 pts
* 10 - One person clearly did more/less than their fair share, but everyone was involved.

