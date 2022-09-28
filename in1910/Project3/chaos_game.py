from __future__ import division
import matplotlib.pyplot as plt
import numpy as np


class ChaosGame:
    def __init__(self, n=3, r=0.5):
        if n < 3:
            raise ValueError("n must be equal or larger than 3")
        if r <= 0 or r >= 1:
            raise ValueError("r must be between 0 and 1")
        self.n = int(n)
        self.r = float(r)
        self.c = None
        self._generate_ngon()
    
    def _generate_ngon(self):
        theta = np.zeros(self.n)
        theta[0] = (2*np.pi)/self.n
        for i in range(self.n-1):
            theta[i+1] = theta[i] + theta[0]
        
        c = np.zeros((self.n, 2))
        for j in range(self.n):
            c[j] = [np.sin(theta[j]), np.cos(theta[j])]
        self.c = c
    
    def plot_ngon(self):
        plt.scatter(*zip(*self.c), s=50, marker='.')
        plt.axis('equal')
        plt.axis('off')
        plt.show()
    
    def _starting_point(self):
        r = np.zeros(self.n)
        for i in range(self.n):
            r[i] = np.random.random()
        su = sum(r)
        scale = 1/su
        r = r*scale
        sp = np.dot(r, self.c)
        return sp
    
    def iterate(self, steps, discard=5):
        n = steps + discard
        X = np.zeros((n, 2))
        X[0] = self._starting_point()
        for i in range(n-1):
            ranint = np.random.randint(0,self.n)
            X[i+1] = self.r*X[i] + (1 - self.r)*self.c[ranint]
        X = X[5:]
        return X
    
    def show(self, steps=10000, discard=5):
        X = self.iterate(steps, discard)
        plt.scatter(*zip(*X), s=0.2, marker='.', c='black')
        plt.axis('equal')
        plt.axis('off')
        plt.show()

    def savepng(self, outfile, steps=10000, discard=5):
        if outfile.endswith(('.png')) == False:
            for i in outfile:
                if i == '.':
                    raise ValueError('Incompatible filename ending. Use .png or no specific ending')
            outfile = outfile+'.png'
        X = self.iterate(steps, discard)
        plt.scatter(*zip(*X), s=0.2, marker='.', c='black')
        plt.axis('equal')
        plt.axis('off')
        plt.savefig(outfile, dpi=300, transparent=True)
        plt.clf()


def check_plot():
    n = [3,4,5,6,7,8]
    for i in n:
        game = ChaosGame(i)
        game.plot_ngon()


def check_sp():
    n = 1000
    game = ChaosGame()
    sp = np.zeros((n, 2))
    for i in range(n):
        sp[i] = game._starting_point()
    plt.scatter(*zip(*sp), s=10, marker='.')
    plt.axis('equal')
    plt.show()


def generate():
    game = ChaosGame(3, 1/2)
    game.savepng('chaos1')
    game = ChaosGame(4, 1/3)
    game.savepng('chaos2.png')
    game = ChaosGame(5, 1/3)
    game.savepng('chaos3.png')
    game = ChaosGame(5, 3/8)
    game.savepng('chaos4.png')
    game = ChaosGame(6, 1/3)
    game.savepng('chaos5.png')