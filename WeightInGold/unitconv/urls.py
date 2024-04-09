from django.urls import path

from . import views

urlpatterns = [
    path('', views.convert, name='convert'),
    path('convert/', views.convert, name='convert'),
]