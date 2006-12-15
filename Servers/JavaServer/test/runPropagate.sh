echo "set the debug mode to false in DBSContants for this script to work properly"
echo "Fetching dataset contents .. "
#out=`./cli.sh api=listDatasetContents path=/aThis_is_a_test_prim/aThis_is_a_test_tier/aCHILD_This_is_a_test_processed block_name="/test/abcest#92357b24-c458-4649-b61e-655939533659"`
out=`./cli.sh api=listDatasetContents path=/This_is_a_test_primary_77197b0f-7ff4-4eb3-a849-a7764905f176/This_is_a_test_tier_SIM_77197b0f-7ff4-4eb3-a849-a7764905f176/CHILD_This_is_a_test_processed_77197b0f-7ff4-4eb3-a849-a7764905f176 "block_name=/test/test#77197b0f-7ff4-4eb3-a849-a7764905f176"`
echo "$out"
echo "Inserting dataset contents .."
./cli.sh api=insertDatasetContents "xmlinput=$out"
