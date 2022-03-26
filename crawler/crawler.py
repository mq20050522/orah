from bs4 import BeautifulSoup

def getContents():
    file = open("St. Anne's-Belfield School - Student Portal.html","r",encoding='utf-8')
    page = file.read()
    file.close()
    soup = BeautifulSoup(page, 'html.parser')
    return soup

def getNames():
    divs = getContents().findAll('div', attrs={'class': 'directory-Entry_Title'})
    names = []
    for i in divs:
        names.append(i.contents[0].strip())
    print(names)
    return names

def getAllContents():
    names = []
    pfp = []

if __name__ == '__main__':
    getNames()