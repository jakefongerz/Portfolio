from django.urls import path

from . import views

urlpatterns = [
    # ex: /gold/
    path('', views.index, name='index'),
    # ex: /polls/5/
    path('plan/', views.plan, name='plan'),

]