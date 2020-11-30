from sql import EasySqlite
import time


class EquipmentModel(object):
    __url_list = []
    __url_dict = {}
    __equipment_list = []
    __equipment_dict = {}
    __version = 3

    __db = None

    def __init__(self):
        self.__db = EasySqlite('../tool.db')
        self.__load()

    def __load(self):
        self.__load_url()
        self.__load_equipment()

    def __load_url(self):
        sql = 'select * from url'
        result = self.__db.execute(sql)
        for row in result:
            url = {}
            url['id'] = row['id']
            url['url'] = row['url']
            url['name'] = row['name']
            url['level'] = row['level']
            self.__add_url(url)

    def __load_equipment(self):
        sql = 'select * from equipment'
        result = self.__db.execute(sql)
        for row in result:
            equipment = {}
            equipment['id'] = row['id']
            equipment['name'] = row['name']
            equipment['type'] = row['type']
            equipment['position'] = row['position']
            equipment['level'] = row['level']
            equipment['enableJobList'] = row['enableJobList']
            equipment['criticalHit'] = row['criticalHit']
            equipment['directHit'] = row['directHit']
            equipment['determination'] = row['determination']
            equipment['faith'] = row['faith']
            equipment['fortitude'] = row['fortitude']
            equipment['skillSpeed'] = row['skillSpeed']
            equipment['spellSpeed'] = row['spellSpeed']
            self.__add_equipment(equipment)

    def get_equipment_list(self):
        return self.__equipment_list

    def get_equipment_by_id(self, id):
        return self.__equipment_dict.get(id)

    def __add_equipment(self, equipment):
        self.__equipment_dict[equipment['id']] = equipment
        self.__equipment_list.append(equipment)

    def get_url_list(self):
        return self.__url_list

    def get_url_by_id(self, id):
        return self.__url_dict.get(id)

    def __add_url(self, url):
        self.__url_dict[url['id']] = url
        self.__url_list.append(url)

    def update_url(self, list):
        sql_head = 'insert into url values'
        sql_values = ''
        for url in list:
            self.__add_url(url)
            sql_values += '('
            sql_values += "'" + url['id'] + "',"
            sql_values += "'" + url['url'] + "',"
            sql_values += "'" + url['name'] + "',"
            sql_values += str(url['level'])
            sql_values += '),'
        sql_values = sql_values.strip(',')
        sql = sql_head + sql_values
        self.__db.execute(sql)

    def insert_equipment(self, equipment):
        sql_head = 'insert into equipment values'
        sql_value = ''
        self.__add_equipment(equipment)
        sql_value += '('
        sql_value += "'" + equipment['id'] + "',"
        sql_value += "'" + equipment['name'] + "',"
        sql_value += str(equipment['type']) + ","
        sql_value += str(equipment['position']) + ","
        sql_value += str(equipment['level']) + ","
        sql_value += "'" + equipment['enableJobList'] + "',"
        sql_value += "'" + equipment.get('クリティカル', 0) + "',"
        sql_value += "'" + equipment.get('ダイレクトヒット', 0) + "',"
        sql_value += "'" + equipment.get('意思力', 0) + "',"
        sql_value += "'" + equipment.get('信仰', 0) + "',"
        sql_value += "'" + equipment.get('スキルスピード', 0) + "',"
        sql_value += "'" + equipment.get('スペルスピード', 0) + "',"
        sql_value += "'" + equipment.get('不屈', 0) + "'"
        sql_value += str(equipment['normalSocket']) + ","
        sql_value += str(equipment['moreSocket']) + ","
        sql_value += "'" + equipment.get('VIT', 0) + "'"
        sql_value += ')'
        sql = sql_head + sql_value
        self.__db.execute(sql)
        sql_update_url = "update url set version=? where id=?"
        self.__db.execute(sql_update_url, [self.__version, equipment['id']])
        print('[{}]insert {} success!'.format(time.strftime("%H:%M:%S", time.localtime()), equipment['name']))

    def update_equipment(self, equipment):
        if self.__equipment_dict.get(equipment['id']) is None:
            self.insert_equipment(equipment)
            return
        sql = 'update equipment set normalSocket=?, moreSocket=?, VIT=? where id=?'
        self.__db.execute(sql,
                          [equipment['normalSocket'], equipment['moreSocket'], equipment.get('VIT', 0),
                           equipment['id']])
        sql_update_url = "update url set version=? where id=?"
        self.__db.execute(sql_update_url, [self.__version, equipment['id']])
        print('[{}]update {} success!'.format(time.strftime("%H:%M:%S", time.localtime()), equipment['name']))

    def update_translator_na(self, list):
        for obj in list:
            self.__update_translator_single_na(obj)
        print('[{}]update translator_na success!'.format(time.strftime("%H:%M:%S", time.localtime())))

    def __update_translator_single_na(self, obj):
        sql = 'update translator set english=? where id=?'
        self.__db.execute(sql, [obj['name'], obj['id']])

    def get_url_undone(self):
        list = []
        sql = 'select * from url where version!=?'
        result = self.__db.execute(sql, [self.__version])
        for row in result:
            url = {}
            url['id'] = row['id']
            url['url'] = row['url']
            url['name'] = row['name']
            url['level'] = row['level']
            list.append(url)
        return list


model = EquipmentModel()
