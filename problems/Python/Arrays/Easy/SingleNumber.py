arr = [1, 2, 2]

def single_number(arr):
    dup = []
    for i in arr:
        if i in dup:
            dup.remove(i)
        else:
            dup.append(i)
    if len(dup) < 1:
        return -1
    return dup[0]

print(single_number(arr))
