const IDB = {
    version: 14,
    schema: {
        groups: '&id,&name,created_at'
    },
    isAuthenticated: false,

    create: function() {
        console.log('creating db')
        fetch('/groups').
            then((resp) => resp.json()).
            then((json) => IDB.db.groups.bulkAdd(json)).
            catch((err) => console.log("ERROR: " + err))
    }
}

IDB.db = new Dexie('orghub');
IDB.db.version(IDB.version).stores(IDB.schema);
