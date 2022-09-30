from __future__ import division
import matplotlib.pyplot as plt
import numpy as np


class AffineTransform:
    def __init__(self, a=0, b=0, c=0, d=0, e=0, f=0):
        self.a = a
        self.b = b
        self.c = c
        self.d = d
        self.e = e
        self.f = f
        
    def __call__(self, x=0, y=0):
        g = [[self.a, self.b], [self.c, self.d]]
        h = [x, y]
        i = [self.e, self.f]
        ans = np.dot(g,h) + i
        return ans


f1 = AffineTransform(d=0.16)
f2 = AffineTransform(0.86, 0.04, -0.04, 0.85, 0, 1.60)
f3 = AffineTransform(0.20, -0.26, 0.23, 0.22, 0, 1.60)
f4 = AffineTransform(-0.15, 0.28, 0.26, 0.24, 0, 0.44)


def fern():
    f = (f1, f2, f3, f4)
    f0 = [0, 1, 2, 3]
    p = [0.01, 0.85, 0.07, 0.07]
    if sum(p) != 1:
        raise ValueError('Sum of p must be 1')
    
    n=50000
    # Generating a list with index values for the affine transformation
    # to pick a random index by given probability
    fp = []
    for i in range(len(p)):
        for j in range(int(p[i]*100)):
            fp.append(f0[i])
            
    def get_f():
        rint = np.random.randint(0,100)
        index = fp[rint]
        return f0[index]
    
    X = np.zeros((n, 2))
    for k in range(n-1):
        ind = get_f()
        X[k+1] = f[ind](X[k][0], X[k][1])
    
    plt.scatter(*zip(*X), s=0.1, marker='.', c='green')
    plt.axis('equal')
    plt.axis('off')
    plt.savefig('fern.png', dpi=300, transparent=True)
    plt.show()