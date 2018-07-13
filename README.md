# CoordinatiorLayoutAnimationBug

This repo serves the purpose of showing an animation bug when we try to do a simple Expand/Contract animation inside Constraint Layout.
The Animation uses the ConstraintSet's of the ConstraintLayout to mark the changes made to views.

The bug only happens when we do the Contract Transition where it is possible to see in the app that the ConstraintLayout doesn't perform the changeBounds animation and always puts the CoordinatorLayout with its final height result which should be handled by the changeBounds Animation. This just happens in the ConstraintLayout since i have another view who performs the ChangeBounds Animation without any problem.


Issue posted on : [Google Issue Tracker](https://issuetracker.google.com/issues/111405625)
