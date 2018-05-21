;; migrations/20180520103942187-initial-schema.clj

(defn up []
  [;; users
   "CREATE TABLE users(
      email varchar(100) NOT NULL UNIQUE,
      name varchar(100) NOT NULL,
      password_digest text NOT NULL,
      created_at timestamp with time zone NOT NULL)"
   "CREATE INDEX users_name_idx ON users(name)"
   "CREATE INDEX users_created_at_idx ON users(created_at)"

   ;; groups
   "CREATE TABLE groups(
      name varchar(100) NOT NULL UNIQUE,
      description text,
      created_at timestamp with time zone NOT NULL)"
   "CREATE INDEX groups_created_at_idx ON groups(created_at)"

   ;; contacts
   "CREATE TABLE contacts(
      name varchar(100) NOT NULL,
      email varchar(100) NOT NULL UNIQUE,
      phone varchar(20),
      zip varchar(10),
      notes text,
      created_at timestamp with time zone NOT NULL)"
   "CREATE INDEX contacts_name_idx ON contacts(name)"
   "CREATE INDEX contacts_created_at_idx ON contacts(created_at)"])

(defn down []
  (let [tables ["users" "groups" "contacts"]]
    (map #(str "DROP TABLE " %) tables)))


;; TODO: add join tables when scoping contacts by group

;; create_table :groups_users, id: false do |t|
;; t.references :group, index: true, null: false
;; t.refejrences :user, index: true, null: false
;; t.datetime :created_at, null: false
;; end
;; add_index :groups_users, [:group_id, :user_id], unique: true
;; add_foreign_key :groups_users, :groups
;; add_foreign_key :groups_users, :users

;; create_table :contacts_groups, id: false do |t|
;; t.references :group, index: true, null: false
;; t.references :contact, index: true, null: false
;; t.datetime :created_at, null: false
;; end
;; add_index :contacts_groups, [:group_id, :contact_id], unique: true
;; add_foreign_key :contacts_groups, :groups
;; add_foreign_key :contacts_groups, :contacts

