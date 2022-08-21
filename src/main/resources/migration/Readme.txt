// update the ${/path/to/src} to the actual path to the project source
// Update database credential in changelog.json
// for window be sure to bind the actual window path with the virtual linux. Then bind the linux path with container.
// Example: D:/git -> bind with /d/git -> bind /d/git with /mongol
docker run --rm -v ${/path/to/src}:/mongol coldze/mongol:latest mongol migrate --path=/mongol/src/main/resources/migration/changelog.json
