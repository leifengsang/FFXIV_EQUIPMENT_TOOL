import requests
from bs4 import BeautifulSoup
from model import model
import time

base_url_jp = 'https://jp.finalfantasyxiv.com/'
base_url_na = 'https://na.finalfantasyxiv.com/'
tab_url_list = []
position_dict = {}
job_dict = {}
un_success_url_list = []


def get_text(url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.193 Safari/537.36',
    }
    try_times = 3
    for i in range(try_times):
        try:
            response = requests.get(url, headers=headers, timeout=30)
            if response.status_code == 200:
                return response.text
        except:
            print(
                '[{}]get url[{}] failed...try {} times'.format(time.strftime("%H:%M:%S", time.localtime()), url, i + 1))
    return None


def get_equipment_by_category(type, lang, ignore):
    index = 1
    while True:
        base_url = None
        if lang == 'jp':
            base_url = base_url_jp
        elif lang == 'na':
            base_url = base_url_na
        url = base_url + 'lodestone/playguide/db/item/?category2={}&page={}'.format(type, index)
        print('[{}]currentUrl:'.format(time.strftime("%H:%M:%S", time.localtime())) + url)
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
            current_url = base_url_jp + a['href']
            name = a.string
            id = current_url.split('/')[-2]
            td = tr.find('td', attrs={'class': 'db-table__body--dark db-table__body--center'})
            level = int(td.string)

            if ignore and model.get_url_by_id(id) is not None:
                continue

            tab_url = {}
            tab_url['id'] = id
            tab_url['url'] = current_url
            tab_url['name'] = name
            tab_url['level'] = level
            tab_url_list.append(tab_url)


def update_equipment_url():
    get_equipment_by_category(1, 'jp', True)  # 武器
    get_equipment_by_category(3, 'jp', True)  # 防具
    get_equipment_by_category(4, 'jp', True)  # 饰品

    model.update_url(tab_url_list)


def update_equipment(tab_url):
    print('[{}]start updating {}...'.format(time.strftime("%H:%M:%S", time.localtime()), tab_url['name']))
    url = tab_url['url']
    text = get_text(url)
    if text is None:
        print('[{}]can not get equipment where url={}'.format(time.strftime("%H:%M:%S", time.localtime()), url))
        un_success_url_list.append(url)
        return
    soup = BeautifulSoup(text, 'lxml')
    equipment = {}
    equipment['id'] = tab_url['id']
    equipment['name'] = tab_url['name']
    equipment['level'] = tab_url['level']
    base_class = 'txt-rarity_'
    if soup.find('h2', attrs={'class': base_class + 'epic'}) is not None:  # 紫装
        equipment['type'] = 1
    elif soup.find('h2', attrs={'class': base_class + 'rare'}) is not None:  # 蓝装
        equipment['type'] = 2
    elif soup.find('h2', attrs={'class': base_class + 'uncommon'}) is not None:  # 绿装
        equipment['type'] = 3
    elif soup.find('h2', attrs={'class': base_class + 'common'}) is not None:  # 白装
        equipment['type'] = 4
    elif soup.find('h2', attrs={'class': base_class + 'magic'}) is not None:  # 粉装
        equipment['type'] = 5
    else:
        equipment['type'] = -1
    position_str = soup.find('p', attrs={'class': 'db-view__item__text__category'}).string
    equipment['position'] = position_dict.get(position_str, -1)
    enable_job_str = soup.find('div', attrs={'class': 'db-view__item_equipment__class'}).string
    enable_job_list = enable_job_str.split(' ')
    enable_job = ''
    for job in enable_job_list:
        if job not in job_dict:
            continue
        enable_job += job_dict[job] + '#'
    equipment['enableJobList'] = enable_job.strip('#')
    if soup.find('div', attrs={'class': 'db-view__item__hq_switch sys_switch_hq'}) is not None:  # HQ
        class_name = 'sys_hq_element'
    else:  # NQ
        class_name = 'sys_nq_element'
    attr_div_list = soup.find_all('div', attrs={'class': class_name})
    for div in attr_div_list:
        if div['class'][0] == class_name:
            attr_ul = div.find('ul', attrs={'class': 'db-view__basic_bonus'})
            if attr_ul is not None:
                break

    if attr_ul is not None:
        attr_li_list = attr_ul.find_all('li')
        for li in attr_li_list:
            text = li.get_text()
            text_info = text.split(' ')
            equipment[text_info[0]] = text_info[1].strip('+')

    # 魔晶石
    equipment['normalSocket'] = len(soup.find_all('li', attrs={'class': 'socket normal'}))
    equipment['moreSocket'] = 1 if soup.find('p', attrs={'class': 'db-view__cannot_materia_prohibition'}) is None and \
                                   equipment['type'] == 3 else 0
    model.update_equipment(equipment)


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
    position_dict['天球儀'] = 1
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

    job_dict['ナイト'] = 'PLD'
    job_dict['戦士'] = 'WAR'
    job_dict['暗黒騎士'] = 'DRK'
    job_dict['ガンブレイカー'] = 'GNB'
    job_dict['モンク'] = 'MNK'
    job_dict['竜騎士'] = 'DRG'
    job_dict['侍'] = 'SAM'
    job_dict['吟遊詩人'] = 'BRD'
    job_dict['忍者'] = 'NIN'
    job_dict['機工士'] = 'MCH'
    job_dict['踊り子'] = 'DNC'
    job_dict['黒魔道士'] = 'BLM'
    job_dict['召喚士'] = 'SMN'
    job_dict['赤魔道士'] = 'RDM'
    job_dict['白魔道士'] = 'WHM'
    job_dict['学者'] = 'MCH'
    job_dict['占星術師'] = 'AST'
    job_dict['全クラス'] = 'ALL'


def update_equipment_undone():
    undone_list = model.get_url_undone()
    for tab_url in undone_list:
        update_equipment(tab_url)
    if len(un_success_url_list) != 0:
        print('unSuccessUrlList(size:{}):'.format(len(un_success_url_list)))
        for url in un_success_url_list:
            print(url)


def update_translator_na():
    get_equipment_by_category(1, 'na', False)  # 武器
    get_equipment_by_category(3, 'na', False)  # 防具
    get_equipment_by_category(4, 'na', False)  # 饰品

    model.update_translator_na(tab_url_list)


def update_translator():
    update_translator_na()


def main():
    constant_init()
    # update_equipment_url()
    # update_equipment_undone()
    # update_translator()


if __name__ == '__main__':
    main()
