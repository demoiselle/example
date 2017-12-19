#!/bin/bash

# Configuration
TOKEN="OkIx0pdcjKXEmI1yVeHCyP71FkixZzxOUglUFz7Zk5EGNFfJUWlsMturNMZNmxBg"
CODE="DB11LITE"

DBPATH="/opt/appfiles"

# ----- DO NOT EDIT ANYTHING BELOW THIS LINE ----- #

error() { echo -e "\e[91m[ERROR: $1]\e[m"; }
success() { echo -e "\e[92m$1\e[m"; }

echo "+--------------------------------------------------+"
echo "|   IP2LOCATION AUTOMATED DATABASE UPDATE SCRIPT   |"
echo "|   ============================================   |"
echo "|        Website: http://www.ip2location.com       |"
echo "|        Contact: support@ip2location.com          |"
echo "+--------------------------------------------------+"
echo ""

echo -n "Check for required commands......................... "

for a in wget unzip wc find grep; do
	if [ -z "$(which $a)" ]; then
		error "Command \"$a\" not found."
		exit 0
	fi
done

success "[OK]"

if [ ! -d /tmp/ip2location ]; then
	echo -n "Create temporary directory.......................... "
	mkdir /tmp/ip2location

	if [ ! -d /tmp/ip2location ]; then
		error "Failed to create /tmp/ip2location"
		exit 0
	fi
	success "[OK]"
fi

cd /tmp/ip2location

echo -n "Download latest database from IP2Location website... "

wget -O database.zip -q http://www.ip2location.com/download?token=$TOKEN\&file=$CODE 2>&1

if [ ! -f database.zip ]; then
	error "Download failed."
	exit 0
fi

if [ ! -z "$(grep 'NO PERMISSION' database.zip)" ]; then
	 error "Permission denied."
        exit 0
fi

if [ ! -z "$(grep '5 times' database.zip)" ]; then
         error "Download quota exceed."
        exit 0
fi

if [ $(wc -c < database.zip) -lt 102400 ]; then
	error "Download failed."
	exit 0	
fi

success "[OK]"

echo -n "Decompress database package......................... "

unzip -q -o database.zip

if [ -z $(find . -name '*.CSV') ]; then
	echo "ERROR:"
	exit 0
fi

NAME="$(find . -name '*.CSV')"

success "[OK]"

echo -n "Move BIN database................................... "

mv -f $NAME $DBPATH

success "[OK]"

echo -n "Perform final clean up.............................. "

rm -rf /tmp/ip2location

success "[OK]"

success "DONE: IP2Location database has been updated."
