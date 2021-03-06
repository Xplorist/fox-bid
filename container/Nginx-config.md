# Nginx-config

* nginx.conf

```

#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;
	client_max_body_size 100M;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
        #listen       80;
		listen        8086;
        server_name  localhost;
		root         html;
		

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        #location / {
        #    #root   html;
		#	root    E:\static;
        #    index  index.html index.htm;
        #}
		
		location / {
			try_files $uri $uri/ /index.html;
		}
		
		# 靜態資源
		location ~ .*\.(png|jpeg|jpg|gif)$ {
			try_files $uri =404;
		}
		
		#項目配置
		location ^~ /bid {
			try_files $uri $uri/ /bid/index.html;
		}
		
		#項目配置
		location ^~ /bidbbs {
			try_files $uri $uri/ /bidbbs/index.html;
		}
		
		#項目對應api代理配置
		location /bid/api/ {
			proxy_pass http://10.244.231.103:14000/;
			proxy_set_header    Host                      $host;
            proxy_set_header    X-Real-IP                 $remote_addr;
            proxy_set_header    X-Forwarded-For           $proxy_add_x_forwarded_for;
            proxy_set_header    HTTP_X_FORWARDED_FOR      $remote_addr;
			proxy_set_header 	Cookie 					  $http_cookie;
		}
		
		#模治检具单点登录
		location /bid/bidsso/ {
			proxy_pass http://10.244.231.103:14000/bidsso/;
			proxy_set_header    Host                      $host;
			proxy_set_header    X-Real-IP                 $remote_addr;
			proxy_set_header    X-Forwarded-For           $proxy_add_x_forwarded_for;
			proxy_set_header    HTTP_X_FORWARDED_FOR      $remote_addr;
			proxy_set_header 	Cookie 					  $http_cookie;
		}
		
		#項目對應api代理配置
		#location /bid/api/ {
		#	proxy_pass http://10.244.231.103:8081/api/;
		#	proxy_set_header    Host                      $host;
        #   proxy_set_header    X-Real-IP                 $remote_addr;
        #    proxy_set_header    X-Forwarded-For           $proxy_add_x_forwarded_for;
        #    proxy_set_header    HTTP_X_FORWARDED_FOR      $remote_addr;
		#	proxy_set_header 	Cookie 					  $http_cookie;
		#}

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}


```