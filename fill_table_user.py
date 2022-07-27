import pandas as pd
import numpy as np

# "UserID"      char(22),
# "UserName"    varchar(50),
# "ReviewCount" int,
# "OnYelpSince" date,

# user
user = pd.read_csv('yelp_academic_dataset_user.csv')
userDF = user[['user_id','name','review_count','yelping_since']]
userDF.to_csv(r'userTable.csv',index = False)

# review
review = pd.read_csv('yelp_academic_dataset_review.csv')
reviewDF = review[['review_id','stars','date','text','business_id','user_id']]
#reviewDF = review[['review_id','stars','date','business_id','user_id']]
reviewDF.to_csv(r'reviewTableText.csv',index = False)

# tips
tips = pd.read_csv('yelp_academic_dataset_tip.csv')
tipsDF = tips[['text','compliment_count','business_id','user_id']]
tipsDF.to_csv(r'tipsTableID.csv')
#tipsDF.to_csv(r'tipsTableNoID.csv',index = False)

# ReviewVotes
rvDF = review[['useful','funny','cool','review_id']]
rvDF.to_csv(r'reviewVotesTableID.csv')
rvDF.to_csv(r'reviewVotesTableNoID.csv',index = False)

