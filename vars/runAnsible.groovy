def call() {
    println("------########---------")
    println(this.env);

    println("------########---------")
    println(env);

    println("------########---------")
    sh '''
        echo "hello World - ${GIT_URL}.${GIT_COMMIT}"

        cat <<EOT >greetings.txt
        [url "git@github.com:"]
        insteadOf = https://github.com/
        
        EOT

        ansible-galaxy install --roles-path="${WORKSPACE}/roles" git+${GIT_URL} --force
        ansible-galaxy install --roles-path="${WORKSPACE}/roles" -r requirements.yml

        ansible-playbook -i 127.0.0.1, wrapper.yml --connection=local \
        -e "role_name=\"${WORKSPACE}\"" 

        # ls roles/
        # env
    '''

}