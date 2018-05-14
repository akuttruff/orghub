importScripts('/js/libs/dexie.min.js', '/js/db.js')

var CACHE_NAME = 'orghub-cache-v2';
var urlsToCache = [
    '/css/custom.css', '/css/pure-min.css', '/css/side-menu.css',
    '/js/libs/axios.js', '/js/libs/dexie.min.js', '/js/libs/vue-dev.js', '/js/libs/vue-router.js',
    '/js/db.js', '/js/index.js', '/js/ui.js',
    '/'
]

self.addEventListener(
    'install', (event) =>
        event.waitUntil(
            caches.open(CACHE_NAME).
                then((cache) => cache.addAll(urlsToCache))))

self.addEventListener(
    'activate', (event) => event.waitUntil(IDB.create()))


// TODO: periodically bust cache to refresh data
self.addEventListener(
    'fetch', (event) =>
              event.respondWith(
                  caches.match(event.request)
                      .then((resp) => resp || fetch(event.request))));
