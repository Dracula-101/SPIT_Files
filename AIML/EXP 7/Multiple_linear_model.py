from sklearn.model_selection import train_test_split
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from sklearn.metrics import r2_score

def Data_example():
    data = pd.read_csv('Fish.csv')
    # copy only first 1000 data
    df = data.copy()
    df.head()
    # Dependant (Target) Variable:
    y = df['Weight']
    # Independant Variables:
    X = df.drop(['Weight', 'Species'], axis=1)
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)
    return X_train, y_train, X_test, y_test
# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


class Multiple_Linear_Regression():
    def __init__(self):
        self.theta = np.zeros(int(np.random.random()), float)[:, np.newaxis]

    def fit(self, X_train, y_train):
        '''
        Fit data to the model based on the matrix approach to Linear Regression. Actual magic happens here!  

        PARAMETERS:
        X_train (numpy.ndarray): Independent variables train data.
        y_train (numpy.ndarray): Dependent variable of train data.

        '''
        X_b = np.c_[np.ones(len(X_train)), X_train]
        theta_bst = np.linalg.pinv(X_b.T.dot(X_b)).dot(X_b.T).dot(y_train)
        self.theta = theta_bst
        return theta_bst

    def predict(self, X_test):
        '''
        Fucntion to predict on new data.

        PARAMETERS:
        X_test (numpy.ndarray): Independent variables data.

        RETURNS:
        y_predict (numpy.ndarray): Predicted dependent variable.
        '''
        X_test = np.c_[np.ones((len(X_test), 1)), X_test]
        y_predict = np.dot(X_test, self.theta)

        return y_predict


def main():
    # -------------------------------------------------------------------------
    # Generate data
    X_train, y_train, X_test, y_test = Data_example()

    # MLR with Gradient Descent
    model = Multiple_Linear_Regression()
    theta = model.fit(X_train, y_train)
    print('Equation for Multiple Linear Regression is')
    print('y = ', theta[0], ' + ', theta[1], '* b0 + ', theta[2], '* b1 + ',
          theta[3], '* b2 + ', theta[4], '* b3 + ', theta[5], '* b4')
    y_preds = model.predict(X_test)

    # Predict values for test and predicted
    print('Initial\t\tPredicted')
    for test, pred in zip(y_test, y_preds):
        # formatted print
        print(f'{test:10.2f} {pred:10.2f}')

    # Find the accuracy of the model
    print('Accuracy of the model is: ', r2_score(y_test, y_preds))


# -----------------------------------------------------------------------------
if __name__ == "__main__":
    main()
# -----------------------------------------------------------------------------
