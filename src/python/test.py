import copy
import sys
from collections import deque

def getKey(item):
    return item[0]

class Foo(object):
    def __init__(self, val):
         self.val = val

    def __repr__(self):
        return str(self.val)


foo = Foo(1)

a = ['foo', foo,'foo',foo]
c = a[:]
d = list(a)
e = copy.copy(a)
f = copy.deepcopy(a)

# edit orignal list and instance
a.append('baz')
foo.val = 5
print("%d %d\n" %(len(a), a.count('foo')))
print('original: %r\n slice: %r\n list(): %r\n copy: %r\n deepcopy: %r'
      % (a, c, d, e, f))
for value in {'one': 1, 'two': 2}:
    print(value)
for element in [1, 2, 3]:
    print(element)
for element in (1, 2, 3):
    print(element)
thisdict = {
    "brand": "Ford",
    "model": "Mustang",
    "model": 1964
}
thisdict["year"] = 2018
print(thisdict["year"])
matrix = [[]]
matrix = [[1 for i in range(5)] for i in range(5)]
print(matrix)
matrix = [[0] * 5] * 5
print(matrix)
a = deque()
a.append(1)
a.append(2)
b = a.popleft()
print("deqeue is %d" %b)
a = {2:"C",1:"B",3:"A"}
print(a)
for key in sorted(a.values(), reverse=True):
    print ("%s: " % (key))
a = ("John", "Charles", "Mike")
b = ("Jenny", "Christy", "Monica", "Vicky")

l = [(2, 3), (6, 7), (3, 34), (24, 64), (1, 43)]
print(l[0])
a = (1,2)