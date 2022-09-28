from chaos_game import ChaosGame
import unittest


class TestChaosGame(unittest.TestCase):

    def test_cNone(self):
        game = ChaosGame()
        self.assertIsNotNone(game.c)


    def test_nr(self):
        game = ChaosGame()
        self.assertIsInstance(game.n, int)
        self.assertIsInstance(game.r, float)


    def test_sp_inside_triangle(self):
        game = ChaosGame()
        sp = game._starting_point()
        c = game.c
        x1, x2, x3, y1, y2, y3 = c[0][0], c[1][0], c[2][0], c[0][1], c[1][1], c[2][1]
        x, y = sp[0], sp[1]
        
        def area(x1, x2, x3, y1, y2, y3):
            return abs((x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2))/2.0)
        
        A = area(x1, x2, x3, y1, y2, y3)
        A1 = area(x, x2, x3, y, y2, y3)
        A2 = area(x1, x, x3, y1, y, y3)
        A3 = area(x1, x2, x, y1, y2, y)
        self.assertEqual(A, A1 + A2 + A3)


    def test_X(self):
        steps = 10
        game = ChaosGame()
        X = game.iterate(steps, discard=5)
        self.assertEqual(len(X), steps)
        self.assertEqual(len(X[0]), 2)


if __name__ == '__main__':
    unittest.main()