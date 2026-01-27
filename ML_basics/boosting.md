

BOOSTING
│
├── Reweighting-based
│   ├── AdaBoost
│   ├── AdaBoost.R
│   └── RobustBoost
│
└── Gradient-based
    ├── First-order
    │   └── GBDT
    │
    └── Second-order
        ├── XGBoost
        ├── LightGBM
        └── CatBoost


Q1 . How to answer “Why so many boosting models?”

“They’re all the same idea.
The differences are in how we define error, how we focus on it, and how much regularization and optimization we add.”

Interviewers love this answer.

Q2. 60-second explanation you can rehearse

“Boosting is a sequential ensemble method where each new model corrects the mistakes of the previous ones. Early methods like AdaBoost reweighted misclassified samples directly. Modern methods use gradient boosting, where we optimize a loss function by fitting weak learners to its gradients. XGBoost, LightGBM, and CatBoost are optimized, regularized implementations of gradient-boosted trees, differing mainly in optimization strategy and categorical handling.”

Q3. Explain Ada boost-

AdaBoost (Adaptive Boosting) is a boosting algorithm that builds a strong classifier by combining many weak classifiers, where:
Each new classifier focuses more on the samples that previous classifiers got wrong.
“Adaptive” = it adapts the importance (weights) of training samples after every iteration.