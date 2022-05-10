from ctypes import string_at
from bs4 import BeautifulSoup
import re
from tqdm import tqdm
import json
import pandas as pd

class console:
    def log(string): print('[LOG]',end=" "); print(string)
    def info(string): print('[INFO]',end=" "); print(string)
    def debug(string): print('[DEBUG]',end=" "); print(string)
    def error(string): print('[ERROR]',end=" "); print(string)

# console.log('hi')

def getContents():
    file = open("St. Anne's-Belfield School - Student Portal.html","r",encoding='utf-8')
    page = file.read()
    file.close()
    soup = BeautifulSoup(page, 'html.parser')
    return soup

contents = getContents()

def getNames():
    console.log("Generating names...")
    divs = contents.findAll('div', attrs={'class': 'directory-Entry_Title'})
    names = []
    for i in divs:
        names.append(i.contents[0].strip())
    # print(names)
    return names

def getGrade():
    console.log("Generating grades...")
    divs = contents.findAll('div', attrs={'class': 'directory-Entry_Tag'})
    grades = []
    for i in divs:
        grades.append(i.contents[0].strip())
    return grades

def getProfile():
    console.log("Generating Profile Pictures...")
    divs = contents.findAll('div', attrs={'class', 'directory-Entry_PersonPhoto--square'})
    urls = []
    for i in divs:
        # print(re.search(r'url\("(.+)"\)', i['style']).group(1))
        urls.append(i['style'][22:-2])
    # console.debug(urls[0])
    return urls

def getAllContents():
    console.log('Generating Json File...')
    data = {}
    data['names'] = getNames()
    data['grades'] = getGrade()
    data['pfp'] = getProfile()
    return data

def getAllFilteredContents():
    console.log('Generating Json File...')
    data = {}
    data['names'] = []
    data['grades'] = []
    data['pfp'] = []
    names = getNames()
    grades = getGrade()
    pfp = getProfile()
    # console.debug(grades)
    filterList = []
    for i in range(len(grades)):
        if grades[i]=='Grade 9' or grades[i]=='Grade 10' or grades[i]=='Grade 11' or grades[i]=='Grade 12': pass
        else: filterList.append(i)
    # console.debug(filterList)
    # console.debug(grades)
    for i in filterList: 
        names[i] = 'deleted'
        grades[i] = 'deleted'
        pfp[i] = 'deleted'
    # console.debug(grades)
    for i in range(len(grades)):
        if names[i] not in data['names']:
            if names[i] != 'deleted': data['names'].append(names[i])
            if grades[i] != 'deleted': data['grades'].append(grades[i])
            if pfp[i] != 'deleted': data['pfp'].append(pfp[i])
    # console.debug(data['grades'])
    return data

def exportJSON():
    contents = getAllFilteredContents()
    console.log("Writing to file...")
    with open("data.json",'w+') as f:
        json.dump(contents, f)

def exportCSV():
    contents = getAllFilteredContents()
    console.log("Writing to file...")
    df = pd.DataFrame(contents)
    # with open("data.csv", 'w+') as f:
    #     df.to_csv(f)
    df.to_csv('data.csv')

if __name__ == '__main__':
    exportJSON()
    exportCSV()