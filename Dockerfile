FROM nginx:1.18.0

## Step 1: Remove all files in html folder in default nginx
RUN rm /usr/share/nginx/html/index.html

## Step 2:
# Copy source code to working directory
COPY Abhinav /usr/share/nginx/Abhinav

## Step 3:
RUN mv /usr/share/nginx/Abhinav/* /usr/share/nginx/html
