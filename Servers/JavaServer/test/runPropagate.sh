echo "set the debug mode to false in DBSContants for this script to work properly"
echo "Fetching dataset contents .. "
out=`./cli.sh api=listDatasetContents path=/aThis_is_a_test_prim/aThis_is_a_test_tier/aCHILD_This_is_a_test_processed block_name="/test/abcest#92357b24-c458-4649-b61e-655939533659"`
echo "$out"
echo "Inserting dataset contents .."
./cli.sh api=insertDatasetContents "xmlinput=$out"
