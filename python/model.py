from sql import EasySqlite


class EquipmentModel(object):
    __url_list = []
    __url_dict = {}
    __db = None

    def __init__(self):
        self.__db = EasySqlite('../tool.db')
        self.__load()

    def __load(self):
        sql = 'select * from url'
        result = self.__db.execute(sql)
        for row in result:
            url = {}
            url['id'] = row['id']
            url['url'] = row['url']
            url['name'] = row['name']
            url['level'] = row['level']
            self.__add_url(url)

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


model = EquipmentModel()
