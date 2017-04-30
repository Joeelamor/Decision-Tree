# Decision Tree

## LizhongZhang lxz160730

Steps to run the program

1. compile the code: 
command: `src javac Main.java`
2. run the class file: 
command: `java Main 1 2 ../dataset/training_set1.csv ../dataset/validation_set1.csv ../dataset/test_set1.csv no`

Example:

```zsh

➜  src ls
DataSet.java              DecisionTreeNode.java    
Gain.java                 PrunDecisionTree.java    
VarianceTrainingSet.java  DecisionTree.java        
EntropyTrainingSet.java   Main.java                
TestingSet.java
➜  src javac Main.java
➜  src ll
total 152
-rw-r--r--  1 zhanglizhong  staff   3.4K Feb 19 20:03 DataSet.class
-rw-r--r--  1 zhanglizhong  staff   3.0K Feb 19 17:50 DataSet.java
-rw-r--r--  1 zhanglizhong  staff   990B Feb 19 20:03 DecisionTree.class
-rw-r--r--  1 zhanglizhong  staff   831B Feb 19 17:50 DecisionTree.java
-rw-r--r--  1 zhanglizhong  staff   3.4K Feb 19 20:03 DecisionTreeNode.class
-rw-r--r--  1 zhanglizhong  staff   4.4K Feb 19 18:30 DecisionTreeNode.java
-rw-r--r--  1 zhanglizhong  staff   2.9K Feb 19 20:03 EntropyTrainingSet.class
-rw-r--r--  1 zhanglizhong  staff   3.7K Feb 19 18:34 EntropyTrainingSet.java
-rw-r--r--  1 zhanglizhong  staff   555B Feb 19 20:03 Gain.class
-rw-r--r--  1 zhanglizhong  staff   426B Feb 19 17:50 Gain.java
-rw-r--r--  1 zhanglizhong  staff   2.1K Feb 19 20:03 Main.class
-rw-r--r--  1 zhanglizhong  staff   2.6K Feb 19 17:48 Main.java
-rw-r--r--  1 zhanglizhong  staff   2.9K Feb 19 20:03 PrunDecisionTree.class
-rw-r--r--  1 zhanglizhong  staff   3.3K Feb 19 17:48 PrunDecisionTree.java
-rw-r--r--  1 zhanglizhong  staff   1.9K Feb 19 20:03 TestingSet.class
-rw-r--r--  1 zhanglizhong  staff   1.9K Feb 19 17:50 TestingSet.java
-rw-r--r--  1 zhanglizhong  staff   2.6K Feb 19 20:03 VarianceTrainingSet.class
-rw-r--r--  1 zhanglizhong  staff   3.3K Feb 19 17:50 VarianceTrainingSet.java
➜  src java Main
Enter input as .\program <L> <K> <training-set> <validation-set> <test-set> <to-print>
➜  src java Main 50 5 ../dataset/training_set1.csv \
	../dataset/validation_set1.csv \
	../dataset/test_set1.csv no
Accuracy for Information Gain Heuristic: 0.7585

Accuracy for Variance Impurity Heuristic: 0.7665

Accuracy for Information Gain Heuristic after Post-Pruning: 0.773

Accuracy for Variance Impurity Heuristic after Post-Pruning: 0.77
```
