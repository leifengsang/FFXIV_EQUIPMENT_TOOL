import requests
from bs4 import BeautifulSoup
from model import model

base_url = 'https://jp.finalfantasyxiv.com/'
tab_url_list = []


def get_text(url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.193 Safari/537.36',
    }
    return requests.get(url, headers=headers).text


def get_equipment_by_category(type):
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
            a = tr.find('a', attrs={'class': 'db_popup db-table__txt--detail_link'})
            # 不是装备tr
            if a is None:
                continue
            current_url = base_url + a['href']
            name = a.string
            id = current_url.split('/')[-2]
            td = tr.find('td', attrs={'class': 'db-table__body--dark db-table__body--center'})
            level = int(td.string)

            if model.get_url_by_id(id) is not None:
                continue

            tab_url = {}
            tab_url['id'] = id
            tab_url['url'] = current_url
            tab_url['name'] = name
            tab_url['level'] = level
            print(tab_url)
            tab_url_list.append(tab_url)


def update_equipment_url():
    get_equipment_by_category(1)
    get_equipment_by_category(3)

    model.update_url(tab_url_list)


def parse(url):
    print(url)
    # text = get_text(url)
    # print(text)


def main():
    # update_equipment_url()
    pass


if __name__ == '__main__':
    main()
