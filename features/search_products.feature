# language: zh-CN
功能:查询宠物
作为一个宠物买家
我想通过宠物的名字进行查询
以便我能够找到我想买的宠物

场景: 查询所有宠物
    假如我在页面"/"
    当我在搜索框中输入""
    而且点击search按钮
    那么我应该看到3个宠物结果:
        |name|description|
        |Reptiles|Cold-blooded friends|
        |Labrador Retriever|Your best friend|
        |Husky|Perfect winter jogging partner|

场景: 查询符合模糊匹配关键词的宠物
    假如我在页面"/"
    当我在搜索框中输入"Husky"
    而且点击search按钮
    那么我应该看到1个宠物结果:
        |name|description|
        |Husky|Perfect winter jogging partner|
