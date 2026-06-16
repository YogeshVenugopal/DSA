class Solution:
    def isPalindrome(self, x: int) -> bool:
        sign = -1 if(x<0) else 1
        if(sign == -1):
            x=x*-1
        temp=str(x)
        rev=temp[::-1]
        result=int(rev)*sign
        if(x == result):
            return True
        else:
            return False
            