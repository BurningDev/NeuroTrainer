# NeuroTrainer
This software product offers psychological tests to monitor cognitive and executive functions.

# Psychological Tests

The most popular test many people would think of is the intelligence test for measuring the IQ (intelligence quotient). This software dosn't contain an entire intelligence test, but tests to measure specific dimensions.

## Stroop

The Stroop test measures the inhibition. Inhibition means the ability to inhibit dominant impulses for action. This is achieved by giving words a different colour than their content meaning (see illustration). We humans specialise in reading rather than naming colours, so reading is our dominant impulse for action. In this test, however, we do not have to react to the meaning of the content, but to the appearance (colour of the writing).

![grafik](https://user-images.githubusercontent.com/19328035/155806143-ca36c169-7394-4a2e-bd19-acc09fb64761.png)

**Results**

Amount: 90
ITEM   |N   |HIT |PER  |TIME
-------+----+----+-----+-------
GREEN   23   23   1.0   634  ms 
RED     25   25   1.0   492  ms 
BLUE    23   23   1.0   574  ms 
YELLOW  19   17   0.89  523  ms 

ITEM: Color
N:    Absolute amount, how often the colour was displayed
HIT:  Absolute amount of successful/correct reactions
PER:  Relative amount of successful/correct reactions
TIME: The time in millisecond someone needs for a correct reaction

## Reaction

The Reaction tests measures the speed. In this test, both circles and squares are randomly displayed. The aim is to react as quickly as possible to the squares and ignore the circles.

**Results**

Amount: 30
HIT  |FA   |COR  |MISS 
-----+-----+-----+-----
15    2     14    0    
0.5   0.07  0.47  0.0  
Avg. Reaction Time (Hit): 213.0 ms

HIT:  Counts all reactions while a stimulus was present
FA:   (False Alarm): Counts all reactions while a stimulus was not present
COR:  (Correct Rejections): Counts all non-responses when no stimulus was present
MISS: Counts all non-responses when a stimulus was present
