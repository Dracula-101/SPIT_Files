# Importing libraries
from sklearn.model_selection import train_test_split
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import plotly.express as px


# Importing dataset
dataset = pd.read_csv('Iris.csv')
print(dataset.head())

# get unique values of species
print("\n\nUnique values of species: ", dataset['Species'].unique())

sepal_length = dataset['SepalLengthCm']
petal_length = dataset['PetalLengthCm']

species = dataset['Species']
data = np.array(list(zip(dataset['SepalLengthCm'], dataset['PetalLengthCm'],)))
print("\n\nData: ", data)


class K_Means:
    def __init__(self, k=3, tol=0.001, max_iter=1000):
        self.k = k
        self.tol = tol
        self.max_iter = max_iter

    # fit method
    def fit(self, data):

        self.centroids = {}
        self.sse = []
        # Initialize the centroids, the first 'k' elements in the dataset will be our initial centroids
        for i in range(self.k):
            self.centroids[i] = data[i]

        # Begin iterations
        for i in range(self.max_iter):
            self.classifications = {}

            # Initialize the classifications dictionary
            for i in range(self.k):
                self.classifications[i] = []

            # Find the distance between the point and cluster; choose the nearest centroid
            for featureset in data:
                distances = [np.linalg.norm(
                    featureset-self.centroids[centroid]) for centroid in self.centroids]
                classification = distances.index(min(distances))
                self.classifications[classification].append(featureset)

            # Previous centroids to compare with current centroids
            prev_centroids = dict(self.centroids)

            # Calculate the mean of the cluster datapoints to re-calculate the centroids
            for classification in self.classifications:
                self.centroids[classification] = np.average(
                    self.classifications[classification], axis=0)

            optimized = True

            # For loop to check if the centroids have moved
            for c in self.centroids:
                original_centroid = prev_centroids[c]
                current_centroid = self.centroids[c]

                # Calculate the SSE
                if np.sum((current_centroid-original_centroid)/original_centroid*100.0) > self.tol:
                    self.sse.append(
                        np.sum((current_centroid-original_centroid)/original_centroid*100.0))
                    optimized = False

            # Break out of the main loop if the results are optimized, ie. the centroids don't change their positions much(more than our tolerance)
            if optimized:
                break

    # predict method
    def predict(self, data):
        # Classify samples as the closest centroid
        distances = [np.linalg.norm(data-self.centroids[centroid])
                     for centroid in self.centroids]
        classification = distances.index(min(distances))
        return classification

# Getting the values and plotting it


def getColor(classification):
    if classification == 0:
        return 'r'
    elif classification == 1:
        return 'g'
    elif classification == 2:
        return 'b'
    else:
        return 'y'


# Main function
if __name__ == '__main__':
    # Initializing the model using K-Means
    model = K_Means(k=3, tol=0.001, max_iter=5000)
    # Training the model
    model.fit(data)

    # Plotting the data for centriod on graph
    for centroid in model.centroids:
        plt.scatter(model.centroids[centroid][0], model.centroids[centroid][1],
                    marker="x", color="k", s=150, linewidths=3)

    # Plotting the data for classification on graph
    for classification in model.classifications:
        # get diff color
        color = getColor(classification)
        # plot the data for featureset
        for featureset in model.classifications[classification]:
            plt.scatter(featureset[0], featureset[1],
                        marker="o", color=color, s=20, linewidths=1)

    plt.show()
    # data from the dataset selecting sepal length and petal length
    data = np.array(list(
        zip(dataset['SepalLengthCm'], dataset['PetalLengthCm'], dataset['Species'])))
    # get random data
    y_pred = data[np.random.choice(data.shape[0], 3, replace=False), :]
    print("\nRandom data: \n", y_pred)
    # predict from the random data
    for value in y_pred:
        # Predict
        prediction = model.predict([float(value[0]), float(value[1])])
        if prediction == 2:
            print("Iris-setosa")
        elif prediction == 1:
            print("Iris-versicolor")
        elif prediction == 0:
            print("Iris-virginica")
