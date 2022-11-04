str = "1-65535"
port = str.split(",")
cnt = 0
for x in port:
    x = x.strip()
    if(x.find("-")!=-1):
        _ = x.split("-")
        for two in range(int(_[0]), int(_[1])+1):
            print(two)
            cnt = cnt+1
    else:
        print(x)
        cnt = cnt + 1

print(cnt)

