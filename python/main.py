import requests
from bs4 import BeautifulSoup

equipment_url_list = []


def get_text(url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.193 Safari/537.36',
    }
    return requests.get(url, headers=headers).text


def get_equpment_by_category(type):
    base_url = 'https://jp.finalfantasyxiv.com/'
    index = 1
    while True:
        url = base_url + 'lodestone/playguide/db/item/?category2={}&page={}'.format(type, index)
        print('currentUrl:' + url)
        index += 1
        text = get_text(url)
        soup = BeautifulSoup(text, 'lxml')
        code404 = soup.find('p', attrs={'class': 'db__result__0__txt'})
        if code404 is not None:
            break
        tr_list = soup.find_all('tr')
        for tr in tr_list:
            td = tr.find('td', attrs={'class': 'db-table__body--dark db-table__body--center'})
            if td is None:
                continue
            level = int(td.string)
            if level < 345:
                continue
            a = tr.find('a', attrs={'class': 'db_popup db-table__txt--detail_link'})
            equipment_url_list.append(base_url + a['href'])


def get_equipment_url():
    base_url = 'https://jp.finalfantasyxiv.com/'
    get_equpment_by_category(1)
    get_equpment_by_category(3)

    # 清空url.txt
    open("url.txt", 'w').close()

    for url in equipment_url_list:
        with open("url.txt", 'a') as f:
            f.write(url + '\n')
        print(url)


def get_url_list_from_file():
    with open('url.txt', 'r') as f:
        while True:
            url = f.readline()
            if url:
                equipment_url_list.append(url.replace('\n', ''))
            else:
                break


def parse(url):
    print(url)
    # text = get_text(url)
    # print(text)


def main():
    # get_equipment_url()
    # get_url_list_from_file()
    # parse(equipment_url_list[0])
    pass


if __name__ == '__main__':
    main()
