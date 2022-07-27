import pandas as pd
import numpy as np

business = pd.read_csv('yelp_academic_dataset_business.csv')

# # business
csv_business = business[['business_id','name','stars','review_count']]
print(csv_business)
csv_business.to_csv(r'businessTable.csv',index = False)

# location 
csv_location = business[['address','city','state','business_id','postal_code']]
print(csv_location)
csv_location.to_csv(r'locationTableRightOne.csv',index = False)

service = ['planning', 'planner', 'services', 'service', 'supplies', 'insurance', 'auto', 'repair', 'beauty', 'health', 'medical']
restaurant = ['restaurants', 'cafes', 'diners', 'pubs', 'bars', 'food trucks']
hospitality = ['hotels', 'travel', 'vacation']
retail = ['shopping', 'stores', 'grocery', 'shops']
entertainment = ['festivals', 'arts', 'music', 'active life', 'nightlife']


serDF = pd.DataFrame( columns= ['BusinessID','Types'])
resDF = pd.DataFrame( columns= ['BusinessID','Types'])
hosDF = pd.DataFrame( columns= ['BusinessID','Types'])
retDF = pd.DataFrame( columns= ['BusinessID','Types'])
entDF = pd.DataFrame( columns= ['BusinessID','Types'])
counter = 0
c = 0
for index in business.index:
    category = str(business['categories'][index]).lower()
    # service
    if any(cat in category for cat in service):
        serDF = serDF.append({ 'BusinessID' : business['business_id'][index], 'Types' : business['categories'][index]}, ignore_index = True)
    # restaurant
    elif any(cat in category for cat in restaurant):
        resDF = resDF.append({ 'BusinessID' : business['business_id'][index], 'Types' : business['categories'][index]}, ignore_index = True)
    # hospitality
    elif any(cat in category for cat in hospitality):
        hosDF = hosDF.append({ 'BusinessID' : business['business_id'][index], 'Types' : business['categories'][index]}, ignore_index = True)
    # retail
    elif any(cat in category for cat in retail):
        retDF = retDF.append({ 'BusinessID' : business['business_id'][index], 'Types' : business['categories'][index]}, ignore_index = True)
    # entertainmment 
    elif any(cat in category for cat in entertainment):
        entDF = entDF.append({ 'BusinessID' : business['business_id'][index], 'Types' : business['categories'][index]}, ignore_index = True)
    else:
        # don't do anything
        counter += 1
    # c += 1
    # if( c > 30 ):
    #     c = 0
    #     break
print(retDF)
print(counter)
