grammer=open('Sql.g','r')
lines=grammer.readlines()
entities_line=""
entityAttr={}
for aline in lines:
	if aline.startswith('entity'):
		aline=aline.split('(')[1].split(')')[0]
		aline=aline.replace("'", "")
		entities=aline.split('|')
		for entity in entities:
			entities_line += " " + entity.strip()

#print entities_line

keymap=open('../src/dbs/search/qb/KeyMap.java','r')
keylines=keymap.readlines()

for akline in keylines:
	if akline.find('map.put') != -1:
		EnAt=akline.split('map.put("')[1].split(',')[0].split('"')[0].split('.')
		if len(EnAt) > 1:
			ent=EnAt[0]
			attr=EnAt[1]
			if ent in entityAttr.keys():
				entityAttr[ent]['attr'].append(attr)
			else: 
				entityAttr[ent]={}
				entityAttr[ent]['attr']=[]
				entityAttr[ent]['attr'].append(attr)
		else : 
			if EnAt[0] not in entityAttr.keys():
				entityAttr[EnAt[0]]={}
				entityAttr[EnAt[0]]['attr']=[]

grammer.close()

print "DBS QL supports following Entinties and their attributes:"
for anEntity in entityAttr.keys():
	print anEntity
	for anAttr in entityAttr[anEntity]['attr']:
		print "	"+anAttr



