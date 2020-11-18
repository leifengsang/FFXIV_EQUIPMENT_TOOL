import requests
from bs4 import BeautifulSoup
from model import model

base_url = 'https://jp.finalfantasyxiv.com/'
tab_url_list = []
position_dict = {}


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
    get_equipment_by_category(1)  # 武器
    get_equipment_by_category(3)  # 防具
    get_equipment_by_category(4)  # 饰品

    model.update_url(tab_url_list)


def parse(url):
    print(url)
    # text = get_text(url)
    # print(text)


def update_equipment(tab_url):
    id = tab_url['name']
    url = tab_url['url']
    if model.get_equipment_by_id(id) is not None:
        return
    text = get_text(url)
    soup = BeautifulSoup(text, 'lxml')
    equipment = {}
    equipment['id'] = tab_url['id']
    equipment['name'] = tab_url['name']
    equipment['level'] = tab_url['level']
    base_class = 'db-view__item__text__name 	txt-rarity_'
    if soup.find('h2', attrs={'class': base_class + 'epic'}):  # 紫装
        equipment['type'] = 1
    elif soup.find('h2', attrs={'class': base_class + 'rare'}):  # 蓝装
        equipment['type'] = 2
    elif soup.find('h2', attrs={'class': base_class + 'uncommon'}):  # 绿装
        equipment['type'] = 3
    elif soup.find('h2', attrs={'class': base_class + 'common'}):  # 白装
        equipment['type'] = 4


def constant_init():
    """
    -1:unknown
    1:武器
    2:副手
    3:头
    4:衣服
    5:手
    6:腰带
    7:裤子
    8:鞋子
    9:耳环
    10:项链
    11:手链
    12:戒指
    """
    position_dict['片手剣'] = 1
    position_dict['両手斧'] = 1
    position_dict['両手剣'] = 1
    position_dict['ガンブレード'] = 1
    position_dict['両手槍'] = 1
    position_dict['両手剣'] = 1
    position_dict['格闘武器'] = 1
    position_dict['刀'] = 1
    position_dict['双剣'] = 1
    position_dict['弓'] = 1
    position_dict['銃'] = 1
    position_dict['投擲武器'] = 1
    position_dict['片手呪具'] = 1
    position_dict['両手呪具'] = 1
    position_dict['魔道書'] = 1
    position_dict['細剣'] = 1
    position_dict['青魔器'] = 1
    position_dict['片手幻具'] = 1
    position_dict['両手幻具'] = 1
    position_dict['魔道書(学者専用)'] = 1
    position_dict['盾'] = 2
    position_dict['頭防具'] = 3
    position_dict['胴防具'] = 4
    position_dict['手防具'] = 5
    position_dict['帯防具'] = 6
    position_dict['脚防具'] = 7
    position_dict['足防具'] = 8
    position_dict['耳飾り'] = 9
    position_dict['首飾り'] = 10
    position_dict['腕輪'] = 11
    position_dict['指輪'] = 12


def main():
    constant_init()
    # update_equipment_url()
    # for tab_url in tab_url_list:
    #     update_equipment(tab_url)


if __name__ == '__main__':
    main()
