from collections import OrderedDict

class LRUCache:

    def __init__(self, capacity: int):
        self.df = OrderedDict()
        self.qantity = capacity

    def get(self, key: int) -> int:
        if key in self.df:
            self.df.move_to_end(key)
            return self.df[key]
        else:
            return -1

    def put(self, key: int, value: int) -> None:
        if key in self.df:
            self.df.move_to_end(key)
        
        self.df[key] = value
        
        if len(self.df)>self.qantity:
            self.df.popitem(last=False)

            




# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)