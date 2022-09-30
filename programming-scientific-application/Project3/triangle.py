from __future__ import division
import matplotlib.pyplot as plt
import numpy as np

h = 1-(0.5**2)
co = [[0,0], [1,0], [0.5,h]]
col = [[1,0,0], [0,0,1], [0,1,0]]


def many_starts():
    n = 1000
    p = np.zeros((n, 2))
    for i in range(n):
        r = np.zeros(3)
        for j in range(3):
            r[j] = np.random.random()
        scale = 1/sum(r)
        r = r*scale
        sp = np.dot(r,co)
        p[i] = sp
    plt.scatter(*zip(*p))
    plt.axis('equal')
    plt.show()


def starting_point():
    r = np.zeros(3)
    for i in range(3):
        r[i] = np.random.random()
    scale = 1/sum(r)
    r = r*scale
    sp = np.dot(r,co)
    return sp


def s_triangle():
    sp = starting_point()
    n = 10005
    p = np.zeros((n, 2))
    p[0] = sp
    for i in range(n-1):
        rint = np.random.randint(0,3)
        cop = co[rint]
        p[i+1] = (p[i] + cop)/2
    p = p[5:]
    plt.scatter(*zip(*p), s=0.1, marker='.')
    plt.axis('equal')
    plt.axis('off')
    plt.show()


def s_triangle2():
    sp = starting_point()
    n = 10005
    colors = np.zeros(n-1)
    p = np.zeros((n, 2))
    p[0] = sp
    for i in range(n-1):
        rint = np.random.randint(0,3)
        colors[i] = rint
        cop = co[rint]
        p[i+1] = (p[i] + cop)/2
    p = p[5:]
    colors = colors[4:]
    red = p[colors == 0]
    blue = p[colors == 1]
    green = p[colors == 2]
    plt.scatter(*zip(*red), s=0.1, marker='.', color='red')
    plt.scatter(*zip(*blue), s=0.1, marker='.', color='blue')
    plt.scatter(*zip(*green), s=0.1, marker='.', color='green')
    plt.axis('equal')
    plt.axis('off')
    plt.show()


def s_triangle3():
    sp = starting_point()
    n = 10005
    colors = np.zeros((n, 3))
    p = np.zeros((n, 2))
    p[0] = sp
    for i in range(n-1):
        rint = np.random.randint(0,3)
        cop = co[rint]
        colp = col[rint]
        colors[i+1] = (colors[i] + colp)/2
        p[i+1] = (p[i] + cop)/2
    p = p[5:]
    colors = colors[5:]
    plt.scatter(*zip(*p), c=colors, s=0.2)
    plt.axis('equal')
    plt.axis('off')
    plt.show()


many_starts()
s_triangle()
s_triangle2()
s_triangle3()

