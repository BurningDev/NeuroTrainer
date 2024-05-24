# NeuroTrainer
This software product offers psychological tests to monitor cognitive and executive functions.

# Psychological Tests
The most popular test many people would think of is the intelligence test for measuring the IQ (intelligence quotient). This software dosn't contain an entire intelligence test, but tests to measure specific dimensions.

## Stroop
The Stroop test measures the inhibition. Inhibition is the ability to inhibit dominant impulses for action. This is achieved by giving words a different colour than their content meaning (see illustration). We humans specialise in reading rather than naming colours, so reading is our dominant impulse for action. In this test, however, we do not have to react to the meaning of the content, but to the appearance (colour of the writing).

![grafik](https://user-images.githubusercontent.com/19328035/155806143-ca36c169-7394-4a2e-bd19-acc09fb64761.png)

**Results**

![grafik](https://user-images.githubusercontent.com/19328035/155808483-e71ddc0c-cbee-44d9-9e38-159412113b9e.png)

1. ITEM:&nbsp;&nbsp; Color<br/>
2. N:&nbsp;&nbsp; Absolute amount, how often the colour was displayed<br/>
3. HIT:&nbsp;&nbsp;  Absolute amount of successful/correct reactions<br/>
4. PER:&nbsp;&nbsp;  Relative amount of successful/correct reactions<br/>
5. TIME:&nbsp;&nbsp; The time in millisecond someone needs for a correct reaction<br/>

## Reaction
The Reaction test measures the cognitive speed. In this test, circles and squares are randomly displayed. The aim is to react as quickly as possible to the squares and ignore the circles.

**Results**

![grafik](https://user-images.githubusercontent.com/19328035/155808500-ab47badf-66ef-4b38-82b4-135ee4677b51.png)

1. HIT:&nbsp;&nbsp;  Counts all reactions while a stimulus was present<br/>
2. FA:&nbsp;&nbsp;   (False Alarm): Counts all reactions while a stimulus was not present<br/>
3. COR:&nbsp;&nbsp;  (Correct Rejections): Counts all non-responses when no stimulus was present<br/>
4. MISS:&nbsp;&nbsp; Counts all non-responses when a stimulus was present<br/>

Recommandation: See "signal detection theory" to understand the concept of false alarms, correct rejections etc.

# Run tests

## Setup
1. Download the project and add it to your IDE
2. Setup the Keys of your controller. See ControllerKeys.java -> initializeKeys()
3. Start the program and enter the name your test (for exmaple: "stroop")

## Good to know
If a test is to be carried out, then make sure that the subjects are in a quiet environment without distractions. In addition, the screen should be set bright enough and has a good contrast so that the colours and shapes can be recognised correctly. People suffering from myopia, especially older subjects, should wear appropriate glasses. Before the actual procedure, the persons should also carry out a test run to get to know the task and learn how to use the controller.

# License
Copyright 2021 BurningDev

Licensed under the MIT-License.

</br></br>
<a href="https://github.com/BurningDev?tab=repositories">
<img src="https://github.com/BurningDev/NeuroTrainer/assets/19328035/8c046678-9b03-421b-9ecb-38aba328c0e9" alt="BurningDev Health Suite Logo" width="300"/></br>
</a>
**Part of BurningDev Health Suite**
