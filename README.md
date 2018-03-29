# LearnAndroid
Android Programming3e

## GeoQuiz
It's the first project in the book.
Coverd by GeoQuizChallengeChapter5

## GeoQuizChallengeChapter2
The challenges of the GeoQuiz in chapter2. **Including**:
- Click TextView to next question.

## GeoQuizChallengeChapter3
The challenges of the GeoQuiz in Chapter3. **Including**:
- Disable buttons to the answered questions.
- Calculate the correct rate after answering all questions.

## GeoQuizChallengeChapter4
There're some operations which can't be applied to Android Studio version 3.0 or higher version. Thus, I may come back to these practices in my holiday.

## GeoQuizChallengeChapter5
These challenges are shown in GeoQuiz.

## GeoQuizChallengeChapter6
The challenges of the GeoQuiz in Chapter6. **Including**:
- Show the API Level in CheatActivity layer.
- Set the maximum times to cheat as 3 and show the remaining times to cheat. When meet this bound, disable the cheat button.

## CriminalIntent
Contains the CriminalIntent app without challenges.

## CriminalIntentChallengeChapter8
The challenge of the CriminalIntent in Chapter8. **Including**:
- Adding an mRequiresPolice variable, and determine which view should be loaded to CrimeAdapter using it by method getItemViewType(int). At method onCreateViewHolder(ViewHolder, int), based on the returned value by method getItemViewType(int), return different ViewHolder. If it's a general crime, then use the original layout. Otherwise, use a layout with a button used to contact police.

## CriminalIntentChallengeChapter9
The challenge of the CriminalIntent in Chapter9. **Including**:
- Use the date format like "Jul 22, 2016"; create and use the date format like "Friday, Jul 22, 2016".

## CriminalIntentChallengeChapter10
The challenges of the CriminalIntent in Chapter10. **Including**:
- Just one crime would be updated once, and thus the method notifyDataChanged is not efficient enough since it will update all visible list items. Try to improve this by only updating the changed crime using method notifyItemChanged(Int).
- The method getCrime(UUID) in CrimeLab is good, but the process of searching the crime ID can be improved. Try to improve the process.

## CriminalIntentChallengeChapter11

The challenges of the CriminalIntent in Chapter11. **Including**:
- The margins of CrimeFragment is missing, since the layout parameters of ViewPager don't support margins setting. Please change the fragment\_crime.xml, to let the margins visible.
- Add two buttons to CrimePagerActivity, allow them to jump to the first crime and last crime. But need to control that when at the first crime, the Jump to First button is supposed to be disabled, and so as the Jump to Last button when at the last crime.
