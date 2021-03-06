/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sagiller.learn.dagger;



import com.sagiller.learn.model.account.AccountManager;
import com.sagiller.learn.model.account.DefaultAccountManager;
import com.sagiller.learn.model.article.ArticleProvider;
import com.sagiller.learn.model.webpage.Webpage;
import com.sagiller.learn.model.webpage.WebpageCategoryProvider;
import com.sagiller.learn.model.webpage.WebpageProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * @author Hannes Dorfmann
 */
@Module public class AppModule {
    private static AccountManager accountManager = new DefaultAccountManager();
    private static ArticleProvider articleProvider = new ArticleProvider();
    private static WebpageCategoryProvider webpageCategoryProvider = new WebpageCategoryProvider();
    private static WebpageProvider webpageProvider = new WebpageProvider();

    @Singleton @Provides public AccountManager providesAccountManager() {
        return accountManager;
    }

    @Singleton @Provides public EventBus providesEventBus() {
        return EventBus.getDefault();
    }

    @Singleton @Provides public ArticleProvider providersArticleProvider() {
        return articleProvider;
    }

    @Singleton @Provides public WebpageCategoryProvider providersWebpageCategoryProvider() {
        return webpageCategoryProvider;
    }

    @Singleton @Provides public WebpageProvider providersWebpageProvider() {
        return webpageProvider;
    }
}
