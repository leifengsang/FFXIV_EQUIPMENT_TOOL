from sql import EasySqlite
import time


class EquipmentModel(object):
    __url_list = []
    __url_dict = {}
    __equipment_list = []
    __equipment_dict = {}
    __food_list = []
    __food_dict = {}
    __version = 6

    __db = None

    def __init__(self):
        self.__db = EasySqlite('../db/tool.db')
        self.__load()

    def __load(self):
        self.__load_url()
        self.__load_equipment()
        self.__load_food()

    def __load_food(self):
        sql = 'select * from food'
        result = self.__db.execute(sql)
        for row in result:
            food = {}
            food['id'] = row['id']
            food['name'] = row['name']
            food['level'] = row['level']
            food['attrType1'] = row['attrType1']
            food['attr1'] = row['attr1']
            food['attrType2'] = row['attrType2']
            food['attr2'] = row['attr2']
            self.__add_food(food)

    def __add_food(self, food):
        self.__food_list.append(food)
        self.__food_dict[food['id']] = food

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
            sql_values += str(url['level']) + ","
            sql_values += str(self.__version) + ","
            sql_values += str(url['type'])
            sql_values += '),'
        sql_values = sql_values.strip(',')
        sql = sql_head + sql_values
        self.__db.execute(sql)

    def __insert_equipment(self, equipment):
        self.__add_equipment(equipment)
        sql_head = 'insert into equipment values'
        sql_value = ''
        sql_value += '('
        sql_value += "'" + equipment['id'] + "',"
        sql_value += "'" + equipment['name'] + "',"
        sql_value += str(equipment['type']) + ","
        sql_value += str(equipment['position']) + ","
        sql_value += str(equipment['level']) + ","
        sql_value += "'" + equipment['enableJobList'] + "',"
        sql_value += "" + str(equipment.get('クリティカル', 0)) + ","
        sql_value += "" + str(equipment.get('ダイレクトヒット', 0)) + ","
        sql_value += "" + str(equipment.get('意思力', 0)) + ","
        sql_value += "" + str(equipment.get('信仰', 0)) + ","
        sql_value += "" + str(equipment.get('スキルスピード', 0)) + ","
        sql_value += "" + str(equipment.get('スペルスピード', 0)) + ","
        sql_value += "" + str(equipment.get('不屈', 0)) + ","
        sql_value += str(equipment['normalSocket']) + ","
        sql_value += str(equipment['moreSocket']) + ","
        sql_value += "'" + str(equipment.get('VIT', 0)) + "'"
        sql_value += ')'
        sql = sql_head + sql_value
        self.__db.execute(sql)
        sql_update_url = "update url set version=? where id=?"
        self.__db.execute(sql_update_url, [self.__version, equipment['id']])
        print('[{}]insert {} success!'.format(time.strftime("%H:%M:%S", time.localtime()), equipment['name']))

    def update_equipment(self, equipment):
        if self.__equipment_dict.get(equipment['id']) is None:
            self.__insert_equipment(equipment)
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

    def get_url_undone(self, type):
        list = []
        sql = 'select * from url where version!=? and type=?'
        result = self.__db.execute(sql, [self.__version, type])
        for row in result:
            url = {}
            url['id'] = row['id']
            url['url'] = row['url']
            url['name'] = row['name']
            url['level'] = row['level']
            list.append(url)
        return list

    def get_url_by_level_and_type(self, level, type):
        list = []
        sql = 'select * from url where version!=? and level>=? and type=?'
        result = self.__db.execute(sql, [self.__version, level, type])
        for row in result:
            url = {}
            url['id'] = row['id']
            url['url'] = row['url']
            url['name'] = row['name']
            url['level'] = row['level']
            list.append(url)
        return list

    def hotfix_get_list(self):
        list = []
        sql = 'select u.* from url as u inner join equipment as e where u.id=e.id and e.type=3 and e.position=12 and u.version!=?'
        result = self.__db.execute(sql, [self.__version])
        for row in result:
            url = {}
            url['id'] = row['id']
            url['url'] = row['url']
            url['name'] = row['name']
            url['level'] = row['level']
            list.append(url)
        return list

    def hotfix(self, id):
        sql = 'update equipment set type=6 where id=?'
        self.__db.execute(sql, [id])
        self.__db.execute(sql, [id + '#copy'])

    def update_food(self, food, ignore=False):
        if self.__food_dict.get(food['id']) is None:
            self.__insert_food(food, ignore)
            return
        print('[{}]update {} success!'.format(time.strftime("%H:%M:%S", time.localtime()), food['name']))

    def __insert_food(self, food, ignore):
        if ignore is False:
            self.__add_food(food)
            sql_head = 'insert into food values'
            sql_value = ''
            sql_value += '('
            sql_value += "'" + food['id'] + "',"
            sql_value += "'" + food['name'] + "',"
            sql_value += str(food['level']) + ","
            sql_value += "'" + food.get('attrType1', '') + "',"
            sql_value += "'" + food.get('attr1', '') + "',"
            sql_value += "'" + food.get('attrType2', '') + "',"
            sql_value += "'" + food.get('attr2', '') + "'"
            sql_value += ')'
            sql = sql_head + sql_value
            self.__db.execute(sql)
        sql_update_url = "update url set version=? where id=?"
        self.__db.execute(sql_update_url, [self.__version, food['id']])
        print('[{}]insert {} success!'.format(time.strftime("%H:%M:%S", time.localtime()), food['name']))

    def get_food_by_id(self, id):
        return self.__food_dict.get(id)


model = EquipmentModel()
